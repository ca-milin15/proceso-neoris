package com.co.neoristransaccional.pruebaneoris.shared.application;

import jakarta.persistence.AttributeConverter;

public class ConverterEstadoEnum implements AttributeConverter<EstadoEnum, Integer>{

	@Override
	public Integer convertToDatabaseColumn(EstadoEnum estadoEnum) {
		return estadoEnum.getCodigo();
	}

	@Override
	public EstadoEnum convertToEntityAttribute(Integer codigo) {
		return EstadoEnum.buscarEnumPorCodigo(codigo, "");
	}

}
