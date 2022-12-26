package com.co.neoristransaccional.pruebaneoris.persona.application;

import jakarta.persistence.AttributeConverter;

public class ConverterGeneroEnum implements AttributeConverter<GeneroEnum, String>{
	

	@Override
	public String convertToDatabaseColumn(GeneroEnum generoEnum) {
		return generoEnum.getCodigo();
	}

	@Override
	public GeneroEnum convertToEntityAttribute(String codigo) {
		return GeneroEnum.buscarEnumPorCodigo(codigo, "" );
	}

}
