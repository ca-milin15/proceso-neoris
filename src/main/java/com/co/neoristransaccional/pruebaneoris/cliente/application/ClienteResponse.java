package com.co.neoristransaccional.pruebaneoris.cliente.application;

import java.io.Serializable;
import java.math.BigInteger;

import com.co.neoristransaccional.pruebaneoris.cliente.domain.Cliente;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClienteResponse implements Serializable{

	private static final long serialVersionUID = 3221561792600520516L;
	
	BigInteger id;
	String estado;
	
	public ClienteResponse(Cliente clienteGuardado) {
		this.id = clienteGuardado.getId();
		this.estado = clienteGuardado.getEstado().getDescripcion();
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}
