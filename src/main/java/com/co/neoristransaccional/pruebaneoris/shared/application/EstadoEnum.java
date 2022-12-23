package com.co.neoristransaccional.pruebaneoris.shared.application;

import java.util.Arrays;

import com.co.neoristransaccional.pruebaneoris.shared.domain.exceptions.EstadoNotFoundRuntimeException;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public enum EstadoEnum {

	ACTIVO (1, "Activo"),
	INACTIVO (0, "Inactivo");
	
	Integer codigo;
	String descripcion;
	
	private EstadoEnum(Integer codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}
	
	public static EstadoEnum buscarEnumPorCodigo(Integer codigo, String mensajeError) {
		return Arrays.asList(EstadoEnum.values()).stream()
				.filter(estado -> estado.getCodigo() == codigo)
				.findFirst().orElseThrow(() -> new EstadoNotFoundRuntimeException(String.format(mensajeError, codigo)));
	}

	public Integer getCodigo() {
		return codigo;
	}


	public String getDescripcion() {
		return descripcion;
	}

}
