package com.co.neoristransaccional.pruebaneoris.movimiento.application;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.co.neoristransaccional.pruebaneoris.cliente.application.ClienteService;
import com.co.neoristransaccional.pruebaneoris.cuenta.application.CuentaService;
import com.co.neoristransaccional.pruebaneoris.cuenta.domain.Cuenta;
import com.co.neoristransaccional.pruebaneoris.movimiento.domain.Movimiento;
import com.co.neoristransaccional.pruebaneoris.movimiento.domain.MovimientoServiceCustomRepo;
import com.co.neoristransaccional.pruebaneoris.movimiento.domain.MovimientoServiceRepository;
import com.co.neoristransaccional.pruebaneoris.movimiento.domain.exceptions.MovimientoReglaNegocioRuntimeException;
import com.co.neoristransaccional.pruebaneoris.movimiento.domain.exceptions.MovimientoReporteSinDatosRuntimeException;
import com.co.neoristransaccional.pruebaneoris.shared.infrastructure.PropiedadesSistemas;

@Service
public class MovimientoServiceImpl implements MovimientoService{

	private final static String NOMBRE_REPORTE = "Estado de cuenta"; 
	MovimientoServiceRepository movimientoServiceRepository;
	PropiedadesSistemas propiedadesSistemas;
	ClienteService clienteService;
	CuentaService cuentaService;
	MovimientoServiceCustomRepo movimientoServiceCustomRepo;
	
	public MovimientoServiceImpl(MovimientoServiceRepository movimientoServiceRepository,
			PropiedadesSistemas propiedadesSistemas, ClienteService clienteService, CuentaService cuentaService,
			MovimientoServiceCustomRepo movimientoServiceCustomRepo) {
		this.movimientoServiceRepository = movimientoServiceRepository;
		this.propiedadesSistemas = propiedadesSistemas;
		this.clienteService = clienteService;
		this.cuentaService = cuentaService;
		this.movimientoServiceCustomRepo = movimientoServiceCustomRepo;
	}

	@Override
	public MovimientoResponse crear(MovimientoRequest movimientoRequest) {
		var cuenta = cuentaService.buscarPorId(movimientoRequest.getIdCuenta());
		validacionesTransaccion(cuenta, movimientoRequest);
		
		var cliente = clienteService.buscarPorId(movimientoRequest.getIdCliente());
		calcularSaldo(movimientoRequest, cuenta);
		var movimiento = new Movimiento(cliente, cuenta, movimientoRequest, propiedadesSistemas);
		var movGuardado = movimientoServiceRepository.save(movimiento);
		cuentaService.guardarCuenta(cuenta);
		return new MovimientoResponse(movGuardado);
	}

	private void validacionesTransaccion(Cuenta cuenta, MovimientoRequest movimientoRequest) {
		var tipoMov =  TipoMovimientoEnum.buscarEnumPorCodigo(movimientoRequest.getTipoMovimiento(), propiedadesSistemas.getExcepciones().getMovimiento().getTipoMovimientoNoEncontrado());
		if(tipoMov == TipoMovimientoEnum.DEBITO) {
			var saldoDisponible = cuenta.getSaldo();
			var valorDebito = movimientoRequest.getValor();
			if(saldoDisponible < valorDebito)
				throw new MovimientoReglaNegocioRuntimeException(String.format(propiedadesSistemas.getExcepciones().getMovimiento().getMovimientoNoDisponibleSinSaldo(), saldoDisponible, valorDebito) );
		}
		
	}

	private void calcularSaldo(MovimientoRequest movimientoRequest, Cuenta cuenta) {
		var tipoMov = TipoMovimientoEnum.buscarEnumPorCodigo(movimientoRequest.getTipoMovimiento(), propiedadesSistemas.getExcepciones().getMovimiento().getTipoMovimientoNoEncontrado());
		var saldo = tipoMov == TipoMovimientoEnum.CREDITO ? cuenta.getSaldo() + movimientoRequest.getValor() : cuenta.getSaldo() - movimientoRequest.getValor();
		cuenta.setSaldo(saldo);
	}

	@Override
	public ReporteMovimiento reportes(LocalDateTime fechaInicio, LocalDateTime fechaFin, BigInteger idCliente) {
		var movimientos = movimientoServiceRepository.findByClienteIdAndFechaBetween(idCliente, fechaInicio, fechaFin);
		if(movimientos.size() > 0 ) 
			return construccionReporte(movimientos);
		throw new MovimientoReporteSinDatosRuntimeException(String.format(propiedadesSistemas.getExcepciones().getMovimiento().getMovimientoReporteSinDatos(), fechaInicio, fechaFin) );
	}
	
	private ReporteMovimiento construccionReporte(List<Movimiento> movimientos) {
		var reporte = new ReporteMovimiento();
		var persona = movimientos.stream().findFirst().get().getCliente().getPersona();
		reporte.setNombreReporte(NOMBRE_REPORTE);
		reporte.setNombreCliente(persona.getNombre());
		reporte.setIdentificacionCliente(persona.getIdentificacion());
		
		var agrupacionPorCuentas = movimientos.stream().collect(Collectors.groupingBy(movimiento -> movimiento.getCuenta()));
		
		reporte.setListaCuentas(agrupacionPorCuentas.values().stream().map(movimientosagrupadosPorCuenta -> {
			var cuentaMovimientosResponse = new CuentaMovimientosResponse();
			cuentaMovimientosResponse.setNumeroCuenta(movimientosagrupadosPorCuenta.stream().findFirst().get().getCuenta().getNumeroCuenta());
			cuentaMovimientosResponse.setMovimientos(movimientosagrupadosPorCuenta.stream().map(movimiento -> {
				return new MovimientoResponse(movimiento);
			}).collect(Collectors.toList()));
			return cuentaMovimientosResponse;
		}).collect(Collectors.toList()));
		
		return reporte;
	}


}
