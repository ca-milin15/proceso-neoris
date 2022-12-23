package com.co.neoristransaccional.pruebaneoris.movimiento.application;

import java.math.BigInteger;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.co.neoristransaccional.pruebaneoris.cliente.application.ClienteService;
import com.co.neoristransaccional.pruebaneoris.cuenta.application.CuentaService;
import com.co.neoristransaccional.pruebaneoris.cuenta.domain.Cuenta;
import com.co.neoristransaccional.pruebaneoris.movimiento.domain.Movimiento;
import com.co.neoristransaccional.pruebaneoris.movimiento.domain.MovimientoServiceRepository;
import com.co.neoristransaccional.pruebaneoris.movimiento.domain.exceptions.MovimientoReglaNegocioRuntimeException;
import com.co.neoristransaccional.pruebaneoris.shared.infrastructure.PropiedadesSistemas;

@Service
public class MovimientoServiceImpl implements MovimientoService{

	MovimientoServiceRepository movimientoServiceRepository;
	PropiedadesSistemas propiedadesSistemas;
	ClienteService clienteService;
	CuentaService cuentaService;
	
	public MovimientoServiceImpl(MovimientoServiceRepository movimientoServiceRepository,
			PropiedadesSistemas propiedadesSistemas, ClienteService clienteService, CuentaService cuentaService) {
		super();
		this.movimientoServiceRepository = movimientoServiceRepository;
		this.propiedadesSistemas = propiedadesSistemas;
		this.clienteService = clienteService;
		this.cuentaService = cuentaService;
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
		return null;
	}


}
