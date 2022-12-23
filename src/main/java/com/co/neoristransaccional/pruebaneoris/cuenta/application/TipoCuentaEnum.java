package com.co.neoristransaccional.pruebaneoris.cuenta.application;

import java.util.Arrays;

import com.co.neoristransaccional.pruebaneoris.shared.domain.exceptions.EstadoNotFoundRuntimeException;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum TipoCuentaEnum {

	AHORROS("CA", "Cuenta de ahorros"),
	CORRIENTE("CC", "Cuenta corriente");
	
	String codigo;
	String descripcion;
	
	private TipoCuentaEnum(String codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}
	
	public static TipoCuentaEnum buscarEnumPorCodigo(String codigo, String mensajeError) {
		return Arrays.asList(TipoCuentaEnum.values()).stream()
				.filter(estado -> estado.getCodigo().equals(codigo))
				.findFirst().orElseThrow(() -> new EstadoNotFoundRuntimeException(String.format(mensajeError, codigo)));
	}
	
	public String getCodigo() {
		return codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	
	
	
}
