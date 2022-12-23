package com.co.neoristransaccional.pruebaneoris.movimiento.application;

import java.util.Arrays;

import com.co.neoristransaccional.pruebaneoris.shared.domain.exceptions.EstadoNotFoundRuntimeException;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum TipoMovimientoEnum {

	CREDITO("CRE", "Credito"),
	DEBITO("DEB", "Debito");
	
	String codigo;
	String descripcion;
	
	private TipoMovimientoEnum(String codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}
	
	public static TipoMovimientoEnum buscarEnumPorCodigo(String codigo, String mensajeError) {
		return Arrays.asList(TipoMovimientoEnum.values()).stream()
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
