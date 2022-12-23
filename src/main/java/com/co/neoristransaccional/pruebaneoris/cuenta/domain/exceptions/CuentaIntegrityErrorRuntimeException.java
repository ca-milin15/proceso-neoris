package com.co.neoristransaccional.pruebaneoris.cuenta.domain.exceptions;

public class CuentaIntegrityErrorRuntimeException extends RuntimeException{

	private static final long serialVersionUID = -4985569979753025512L;

	public CuentaIntegrityErrorRuntimeException(String message) {
		super(message);
	}

}
