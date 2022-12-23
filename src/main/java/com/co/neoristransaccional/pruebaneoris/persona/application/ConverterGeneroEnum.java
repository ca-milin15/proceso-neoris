package com.co.neoristransaccional.pruebaneoris.persona.application;

import org.springframework.beans.factory.annotation.Autowired;

import com.co.neoristransaccional.pruebaneoris.shared.infrastructure.PropiedadesSistemas;

import jakarta.persistence.AttributeConverter;

public class ConverterGeneroEnum implements AttributeConverter<GeneroEnum, String>{
	
	@Autowired
	PropiedadesSistemas propiedadesSistemas;

	@Override
	public String convertToDatabaseColumn(GeneroEnum generoEnum) {
		return generoEnum.getCodigo();
	}

	@Override
	public GeneroEnum convertToEntityAttribute(String codigo) {
		return GeneroEnum.buscarEnumPorCodigo(codigo, String.format(propiedadesSistemas.getExcepciones().getCliente().getGeneroNoEncontrado(), codigo) );
	}

}
