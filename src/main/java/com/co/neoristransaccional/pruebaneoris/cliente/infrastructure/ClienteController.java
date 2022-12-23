package com.co.neoristransaccional.pruebaneoris.cliente.infrastructure;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.co.neoristransaccional.pruebaneoris.cliente.application.ClienteRequest;
import com.co.neoristransaccional.pruebaneoris.cliente.application.ClienteResponse;
import com.co.neoristransaccional.pruebaneoris.cliente.application.ClienteService;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("clientes")
public class ClienteController {

	@Autowired
	ClienteService clienteService;
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.OK)
	public ClienteResponse crear(@RequestBody ClienteRequest clienteRequest) {
		return clienteService.crear(clienteRequest);
	}

	@PutMapping("{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ClienteResponse actualizar(@PathVariable BigInteger id, @RequestBody ClienteRequest clienteRequest) {
		return clienteService.actualizar(id, clienteRequest);
	}

	@DeleteMapping("{idCliente}")
	@ResponseStatus(value = HttpStatus.OK)
	public Boolean eliminar(@PathVariable BigInteger idCliente) {
		return clienteService.eliminar(idCliente);
	}


}
