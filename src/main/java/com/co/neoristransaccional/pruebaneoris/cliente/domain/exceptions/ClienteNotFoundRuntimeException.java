package com.co.neoristransaccional.pruebaneoris.cliente.domain.exceptions;

public class ClienteNotFoundRuntimeException extends RuntimeException{

	private static final long serialVersionUID = -8600195331318880663L;

	public ClienteNotFoundRuntimeException(String message) {
		super(message);
	}

}
