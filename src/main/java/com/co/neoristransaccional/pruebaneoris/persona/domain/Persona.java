package com.co.neoristransaccional.pruebaneoris.persona.domain;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import com.co.neoristransaccional.pruebaneoris.cliente.application.ClienteRequest;
import com.co.neoristransaccional.pruebaneoris.persona.application.ConverterGeneroEnum;
import com.co.neoristransaccional.pruebaneoris.persona.application.GeneroEnum;
import com.co.neoristransaccional.pruebaneoris.shared.domain.EntidadGeneral;
import com.co.neoristransaccional.pruebaneoris.shared.infrastructure.PropiedadesSistemas;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "persona")
public class Persona extends EntidadGeneral{

	private static final long serialVersionUID = 1300961659836211780L;
	
	String nombre;

	@Convert(converter = ConverterGeneroEnum.class)
	GeneroEnum genero;
	
	int edad;
	String identificacion;
	String direccion;
	String telefono;
	
	public Persona() {
		super();
	}

	public Persona(ClienteRequest clienteRequest, PropiedadesSistemas propiedadesSistemas) {
		this.nombre = clienteRequest.getNombre();
		this.genero = GeneroEnum.buscarEnumPorCodigo(clienteRequest.getGenero(), 
				String.format(propiedadesSistemas.getExcepciones().getCliente().getGeneroNoEncontrado(), clienteRequest.getGenero()) );
		this.edad = clienteRequest.getEdad();
		this.identificacion = clienteRequest.getIdentificacion();
		this.direccion = clienteRequest.getDireccion();
		this.telefono = clienteRequest.getTelefono();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public GeneroEnum getGenero() {
		return genero;
	}

	public void setGenero(GeneroEnum genero) {
		this.genero = genero;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
}
