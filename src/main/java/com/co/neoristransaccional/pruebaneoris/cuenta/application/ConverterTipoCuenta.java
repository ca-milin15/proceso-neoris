package com.co.neoristransaccional.pruebaneoris.cuenta.application;

import jakarta.persistence.AttributeConverter;

public class ConverterTipoCuenta implements AttributeConverter<TipoCuentaEnum, String>{

	@Override
	public String convertToDatabaseColumn(TipoCuentaEnum attribute) {
		return attribute.getCodigo();
	}

	@Override
	public TipoCuentaEnum convertToEntityAttribute(String codigo) {
		return TipoCuentaEnum.buscarEnumPorCodigo(codigo, "");
	}

}
