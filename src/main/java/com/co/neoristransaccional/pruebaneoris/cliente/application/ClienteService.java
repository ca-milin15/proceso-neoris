package com.co.neoristransaccional.pruebaneoris.cliente.application;

import java.math.BigInteger;

import com.co.neoristransaccional.pruebaneoris.cliente.domain.Cliente;

public interface ClienteService {

	ClienteResponse crear (ClienteRequest clienteRequest);
	ClienteResponse actualizar (BigInteger id, ClienteRequest clienteRequest);
	Boolean eliminar (BigInteger idCliente);
	Cliente buscarPorId (BigInteger idCliente);
}
