package com.co.neoristransaccional.pruebaneoris.cuenta.application;

import java.math.BigInteger;

import org.springframework.stereotype.Service;

import com.co.neoristransaccional.pruebaneoris.cliente.application.ClienteService;
import com.co.neoristransaccional.pruebaneoris.cliente.domain.Cliente;
import com.co.neoristransaccional.pruebaneoris.cuenta.domain.Cuenta;
import com.co.neoristransaccional.pruebaneoris.cuenta.domain.CuentaServiceRepository;
import com.co.neoristransaccional.pruebaneoris.cuenta.domain.exceptions.CuentaIntegrityErrorRuntimeException;
import com.co.neoristransaccional.pruebaneoris.cuenta.domain.exceptions.CuentaNotFoundRuntimeException;
import com.co.neoristransaccional.pruebaneoris.shared.application.EstadoEnum;
import com.co.neoristransaccional.pruebaneoris.shared.infrastructure.PropiedadesSistemas;

@Service
public class CuentaServiceImpl implements CuentaService {

	CuentaServiceRepository cuentaServiceRepository;
	PropiedadesSistemas propiedadesSistemas;
	ClienteService clienteService;
	
	
	public CuentaServiceImpl(CuentaServiceRepository cuentaServiceRepository, PropiedadesSistemas propiedadesSistemas, ClienteService clienteService) {
		super();
		this.cuentaServiceRepository = cuentaServiceRepository;
		this.propiedadesSistemas = propiedadesSistemas;
		this.clienteService = clienteService;
	}

	@Override
	public CuentaResponse crear(CuentaRequest cuentaRequest) {
		Cliente cliente = clienteService.buscarPorId(cuentaRequest.getIdCliente());
		Cuenta cuenta = new Cuenta(cuentaRequest, cliente, propiedadesSistemas);
		return new CuentaResponse(cuentaServiceRepository.save(cuenta));
	}

	@Override
	public CuentaResponse actualizar(BigInteger id, CuentaRequest cuentaRequest) {
		Cuenta cuenta = buscarCuentaPorId(id);
		cuenta = actualizarAtributos(cuenta, cuentaRequest);
		return new CuentaResponse(cuentaServiceRepository.save(cuenta));
	}
	
	private Cuenta actualizarAtributos (Cuenta cuenta, CuentaRequest cuentaRequest) {
		cuenta.setNumeroCuenta(cuentaRequest.getNumeroCuenta());
		cuenta.setTipoCuenta(TipoCuentaEnum.buscarEnumPorCodigo(cuentaRequest.getTipoCuenta(), propiedadesSistemas.getExcepciones().cuenta.getTipoCuentaNoEncontrada()));
		cuenta.setEstado(EstadoEnum.buscarEnumPorCodigo(cuentaRequest.getEstado(), propiedadesSistemas.getExcepciones().getCliente().getEstadoNoValido()));
		return cuenta;
	}
	
	private Cuenta buscarCuentaPorId (BigInteger id) {
		return cuentaServiceRepository
				.findById(id)
				.orElseThrow(() -> new CuentaNotFoundRuntimeException(String.format(propiedadesSistemas.getExcepciones().getCuenta().getErrorEntidadNoEncontrada(), id)));
	}
	
	@Override
	public Boolean eliminar(BigInteger id) {
		Cuenta cuenta = buscarCuentaPorId(id);
		try {
			cuentaServiceRepository.delete(cuenta);
			return true;
		} catch (Exception e) {
			throw new CuentaIntegrityErrorRuntimeException(propiedadesSistemas.excepciones.cuenta.tipoCuentaErrorIntegridad);
		}
	}

	@Override
	public Cuenta buscarPorId(BigInteger id) {
		return buscarCuentaPorId(id);
	}

	@Override
	public Cuenta guardarCuenta(Cuenta cuenta) {
		return cuentaServiceRepository.save(cuenta);
	}

}
