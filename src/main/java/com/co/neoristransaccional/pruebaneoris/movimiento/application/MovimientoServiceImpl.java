package com.co.neoristransaccional.pruebaneoris.movimiento.application;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.co.neoristransaccional.pruebaneoris.cliente.application.ClienteService;
import com.co.neoristransaccional.pruebaneoris.cliente.domain.Cliente;
import com.co.neoristransaccional.pruebaneoris.cuenta.application.CuentaService;
import com.co.neoristransaccional.pruebaneoris.cuenta.domain.Cuenta;
import com.co.neoristransaccional.pruebaneoris.movimiento.domain.Movimiento;
import com.co.neoristransaccional.pruebaneoris.movimiento.domain.MovimientoServiceRepository;
import com.co.neoristransaccional.pruebaneoris.movimiento.domain.exceptions.MovimientoReglaNegocioRuntimeException;
import com.co.neoristransaccional.pruebaneoris.movimiento.domain.exceptions.MovimientoReporteSinDatosRuntimeException;
import com.co.neoristransaccional.pruebaneoris.persona.domain.Persona;
import com.co.neoristransaccional.pruebaneoris.shared.infrastructure.PropiedadesSistemas;

@Service
public class MovimientoServiceImpl implements MovimientoService{

	private final static String NOMBRE_REPORTE = "Estado de cuenta"; 
	MovimientoServiceRepository movimientoServiceRepository;
	PropiedadesSistemas propiedadesSistemas;
	ClienteService clienteService;
	CuentaService cuentaService;
	
	public MovimientoServiceImpl(MovimientoServiceRepository movimientoServiceRepository,
			PropiedadesSistemas propiedadesSistemas, ClienteService clienteService, CuentaService cuentaService) {
		this.movimientoServiceRepository = movimientoServiceRepository;
		this.propiedadesSistemas = propiedadesSistemas;
		this.clienteService = clienteService;
		this.cuentaService = cuentaService;
	}

	@Override
	public MovimientoResponse crear(MovimientoRequest movimientoRequest) {
		Cuenta cuenta = cuentaService.buscarPorId(movimientoRequest.getIdCuenta());
		validacionesTransaccion(cuenta, movimientoRequest);
		
		Cliente cliente = clienteService.buscarPorId(movimientoRequest.getIdCliente());
		calcularSaldo(movimientoRequest, cuenta);
		Movimiento movimiento = new Movimiento(cliente, cuenta, movimientoRequest, propiedadesSistemas);
		Movimiento movGuardado = movimientoServiceRepository.save(movimiento);
		cuentaService.guardarCuenta(cuenta);
		return new MovimientoResponse(movGuardado);
	}

	private void validacionesTransaccion(Cuenta cuenta, MovimientoRequest movimientoRequest) {
		TipoMovimientoEnum tipoMov =  TipoMovimientoEnum.buscarEnumPorCodigo(movimientoRequest.getTipoMovimiento(), propiedadesSistemas.getExcepciones().getMovimiento().getTipoMovimientoNoEncontrado());
		if(tipoMov == TipoMovimientoEnum.DEBITO) {
			Double saldoDisponible = cuenta.getSaldo();
			Double valorDebito = movimientoRequest.getValor();
			if(saldoDisponible < valorDebito)
				throw new MovimientoReglaNegocioRuntimeException(String.format(propiedadesSistemas.getExcepciones().getMovimiento().getMovimientoNoDisponibleSinSaldo(), saldoDisponible, valorDebito) );
		}
		
	}

	private void calcularSaldo(MovimientoRequest movimientoRequest, Cuenta cuenta) {
		TipoMovimientoEnum tipoMov = TipoMovimientoEnum.buscarEnumPorCodigo(movimientoRequest.getTipoMovimiento(), propiedadesSistemas.getExcepciones().getMovimiento().getTipoMovimientoNoEncontrado());
		Double saldo = tipoMov == TipoMovimientoEnum.CREDITO ? cuenta.getSaldo() + movimientoRequest.getValor() : cuenta.getSaldo() - movimientoRequest.getValor();
		cuenta.setSaldo(saldo);
	}

	@Override
	public ReporteMovimiento reportes(LocalDateTime fechaInicio, LocalDateTime fechaFin, BigInteger idCliente) {
		List<Movimiento> movimientos = movimientoServiceRepository.findByClienteIdAndFechaBetween(idCliente, fechaInicio, fechaFin);
		if(movimientos.size() > 0 ) 
			return construccionReporte(movimientos);
		throw new MovimientoReporteSinDatosRuntimeException(String.format(propiedadesSistemas.getExcepciones().getMovimiento().getMovimientoReporteSinDatos(), fechaInicio, fechaFin) );
	}
	
	private ReporteMovimiento construccionReporte(List<Movimiento> movimientos) {
		ReporteMovimiento reporte = new ReporteMovimiento();
		Persona persona = movimientos.stream().findFirst().get().getCliente().getPersona();
		reporte.setNombreReporte(NOMBRE_REPORTE);
		reporte.setNombreCliente(persona.getNombre());
		reporte.setIdentificacionCliente(persona.getIdentificacion());
		
		Map<Object, List<Movimiento>> agrupacionPorCuentas = movimientos.stream().collect(Collectors.groupingBy(movimiento -> movimiento.getCuenta()));
		
		reporte.setListaCuentas(agrupacionPorCuentas.values().stream().map(movimientosagrupadosPorCuenta -> {
			CuentaMovimientosResponse cuentaMovimientosResponse = new CuentaMovimientosResponse();
			cuentaMovimientosResponse.setNumeroCuenta(movimientosagrupadosPorCuenta.stream().findFirst().get().getCuenta().getNumeroCuenta());
			cuentaMovimientosResponse.setMovimientos(movimientosagrupadosPorCuenta.stream().map(movimiento -> {
				return new MovimientoResponse(movimiento);
			}).collect(Collectors.toList()));
			return cuentaMovimientosResponse;
		}).collect(Collectors.toList()));
		
		return reporte;
	}


}
