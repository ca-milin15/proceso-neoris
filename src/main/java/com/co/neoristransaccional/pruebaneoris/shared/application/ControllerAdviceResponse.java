package com.co.neoristransaccional.pruebaneoris.shared.application;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ControllerAdviceResponse {

	String mensaje;
	
	public ControllerAdviceResponse(String message) {
		this.mensaje = message;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
