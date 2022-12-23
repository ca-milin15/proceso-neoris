package com.co.neoristransaccional.pruebaneoris.cuenta.domain.exceptions;

public class CuentaNotFoundRuntimeException extends RuntimeException{

	private static final long serialVersionUID = -8067342277881319240L;

	public CuentaNotFoundRuntimeException() {
		super();
	}

	public CuentaNotFoundRuntimeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CuentaNotFoundRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public CuentaNotFoundRuntimeException(String message) {
		super(message);
	}

	public CuentaNotFoundRuntimeException(Throwable cause) {
		super(cause);
	}
	
	

}
