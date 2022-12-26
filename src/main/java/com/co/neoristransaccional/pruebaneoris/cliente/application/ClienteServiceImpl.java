package com.co.neoristransaccional.pruebaneoris.cliente.application;

import java.math.BigInteger;

import org.springframework.stereotype.Service;

import com.co.neoristransaccional.pruebaneoris.cliente.domain.Cliente;
import com.co.neoristransaccional.pruebaneoris.cliente.domain.ClienteServiceRepository;
import com.co.neoristransaccional.pruebaneoris.cliente.domain.exceptions.ClienteNotFoundRuntimeException;
import com.co.neoristransaccional.pruebaneoris.cuenta.domain.exceptions.CuentaIntegrityErrorRuntimeException;
import com.co.neoristransaccional.pruebaneoris.persona.domain.Persona;
import com.co.neoristransaccional.pruebaneoris.shared.application.EstadoEnum;
import com.co.neoristransaccional.pruebaneoris.shared.infrastructure.PropiedadesSistemas;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClienteServiceImpl implements ClienteService{

	ClienteServiceRepository clienteServiceRepository;
	PropiedadesSistemas propiedadesSistemas;

	public ClienteServiceImpl(ClienteServiceRepository clienteServiceRepository, PropiedadesSistemas propiedadesSistemas) {
		this.clienteServiceRepository = clienteServiceRepository;
		this.propiedadesSistemas = propiedadesSistemas;
	}

	@Override
	public ClienteResponse crear(ClienteRequest clienteRequest) {
		Persona persona = new Persona(clienteRequest, propiedadesSistemas);
		Cliente cliente = new Cliente(clienteRequest, persona);
		Cliente clienteGuardado = clienteServiceRepository.save(cliente);
		return new ClienteResponse(clienteGuardado);
	}

	@Override
	public ClienteResponse actualizar(BigInteger id, ClienteRequest clienteRequest) {
		Cliente cliente = buscarClientePorId(id);
		cliente.setContrasena(clienteRequest.getContrasena());
		cliente.setEstado(EstadoEnum.buscarEnumPorCodigo(clienteRequest.getEstado(), propiedadesSistemas.getExcepciones().cliente.getEstadoNoValido()));
		return new ClienteResponse(clienteServiceRepository.save(cliente));
	}

	private Cliente buscarClientePorId (BigInteger id) {
		return clienteServiceRepository
				.findById(id)
				.orElseThrow(() -> new ClienteNotFoundRuntimeException(String.format(propiedadesSistemas.getExcepciones().getCliente().getErrorEntidadNoEncontrada(), id)));
	}
	
	@Override
	public Boolean eliminar(BigInteger idCliente) {
		Cliente cliente = buscarClientePorId(idCliente);
		try {
			clienteServiceRepository.delete(cliente);
			return true;
		} catch (Exception e) {
			throw new CuentaIntegrityErrorRuntimeException(propiedadesSistemas.excepciones.cuenta.tipoCuentaErrorIntegridad);
		}
	}

	@Override
	public Cliente buscarPorId(BigInteger idCliente) {
		return buscarClientePorId(idCliente);
	}

}
