package com.co.neoristransaccional.pruebaneoris.persona.application;

import java.util.Arrays;

import com.co.neoristransaccional.pruebaneoris.persona.domain.exception.GeneroEnumNotFound;

public enum GeneroEnum {
	
	MASCULINO("M", "Masculino"),
	FEMENINO("F", "Femenino");
	
	String codigo;
	String descripcion;
	
	private GeneroEnum(String codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}
	
	public static GeneroEnum buscarEnumPorCodigo(String codigo, String mensajeError) {
		return Arrays.asList(GeneroEnum.values()).stream()
				.filter(estado -> estado.getCodigo().equals(codigo))
				.findFirst().orElseThrow(() -> new GeneroEnumNotFound(mensajeError));
	}
	
	public String getCodigo() {
		return codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	
}
