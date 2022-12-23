package com.co.neoristransaccional.pruebaneoris.movimiento.application;

import jakarta.persistence.AttributeConverter;

public class ConverterTipoMovimiento implements AttributeConverter<TipoMovimientoEnum, String>{

	@Override
	public String convertToDatabaseColumn(TipoMovimientoEnum attribute) {
		if(attribute == null)
			return null;
		return attribute.getCodigo();
	}

	@Override
	public TipoMovimientoEnum convertToEntityAttribute(String codigo) {
		return TipoMovimientoEnum.buscarEnumPorCodigo(codigo, "");
	}

}
