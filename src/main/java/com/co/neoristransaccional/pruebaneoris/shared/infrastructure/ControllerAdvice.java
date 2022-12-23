package com.co.neoristransaccional.pruebaneoris.shared.infrastructure;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.co.neoristransaccional.pruebaneoris.cliente.domain.exceptions.ClienteNotFoundRuntimeException;
import com.co.neoristransaccional.pruebaneoris.cuenta.domain.exceptions.CuentaNotFoundRuntimeException;
import com.co.neoristransaccional.pruebaneoris.movimiento.domain.exceptions.MovimientoReporteSinDatosRuntimeException;
import com.co.neoristransaccional.pruebaneoris.persona.domain.exception.GeneroEnumNotFoundRuntimeException;
import com.co.neoristransaccional.pruebaneoris.shared.application.ControllerAdviceResponse;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler{

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ControllerAdviceResponse> runtimeExceptionDefault(RuntimeException ex, WebRequest request){
		var objetoRespuesta = new ControllerAdviceResponse(ex.getMessage());
		return new ResponseEntity<>(objetoRespuesta, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(CuentaNotFoundRuntimeException.class)
	public ResponseEntity<ControllerAdviceResponse> cuentaNotFoundRuntimeException(RuntimeException ex, WebRequest request){
		var objetoRespuesta = new ControllerAdviceResponse(ex.getMessage());
		return new ResponseEntity<>(objetoRespuesta, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ClienteNotFoundRuntimeException.class)
	public ResponseEntity<ControllerAdviceResponse> clienteNotFoundRuntimeException(RuntimeException ex, WebRequest request){
		var objetoRespuesta = new ControllerAdviceResponse(ex.getMessage());
		return new ResponseEntity<>(objetoRespuesta, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(GeneroEnumNotFoundRuntimeException.class)
	public ResponseEntity<ControllerAdviceResponse> generoEnumNotFound(RuntimeException ex, WebRequest request){
		var objetoRespuesta = new ControllerAdviceResponse(ex.getMessage());
		return new ResponseEntity<>(objetoRespuesta, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(MovimientoReporteSinDatosRuntimeException.class)
	public ResponseEntity<ControllerAdviceResponse> movimientoReporteSinDatosRuntimeException(RuntimeException ex, WebRequest request){
		var objetoRespuesta = new ControllerAdviceResponse(ex.getMessage());
		return new ResponseEntity<>(objetoRespuesta, HttpStatus.NOT_FOUND);
	}
	
	
}
