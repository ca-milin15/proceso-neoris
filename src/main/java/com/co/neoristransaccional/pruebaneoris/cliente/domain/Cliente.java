package com.co.neoristransaccional.pruebaneoris.cliente.domain;


import java.util.List;

import com.co.neoristransaccional.pruebaneoris.cliente.application.ClienteRequest;
import com.co.neoristransaccional.pruebaneoris.cuenta.domain.Cuenta;
import com.co.neoristransaccional.pruebaneoris.movimiento.domain.Movimiento;
import com.co.neoristransaccional.pruebaneoris.persona.domain.Persona;
import com.co.neoristransaccional.pruebaneoris.shared.application.ConverterEstadoEnum;
import com.co.neoristransaccional.pruebaneoris.shared.application.EstadoEnum;
import com.co.neoristransaccional.pruebaneoris.shared.domain.EntidadGeneral;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "cliente")
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends EntidadGeneral {

	private static final long serialVersionUID = -3996121077023882951L;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="idPersona")
	Persona persona;
	

	@OneToMany
	@JoinColumn(name="idCliente")
	List<Cuenta> listaCuenta;

	@OneToMany
	@JoinColumn(name="idCliente")
	List<Movimiento> listaMovimiento;
	
	@Column(name="contrasena")
	String contrasena;
	
	@Column(name="estado")
	@Convert(converter = ConverterEstadoEnum.class)
	EstadoEnum estado;
	

	public Cliente() {
		super();
	}

	public Cliente(ClienteRequest clienteRequest, Persona persona) {
		super();
		this.persona = persona;
		this.contrasena = clienteRequest.getContrasena();
		this.estado = EstadoEnum.ACTIVO;
	}


	public EstadoEnum getEstado() {
		return estado;
	}


	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}


	public Persona getPersona() {
		return persona;
	}


	public void setPersona(Persona persona) {
		this.persona = persona;
	}


	public List<Cuenta> getListaCuenta() {
		return listaCuenta;
	}


	public void setListaCuenta(List<Cuenta> listaCuenta) {
		this.listaCuenta = listaCuenta;
	}


	public List<Movimiento> getListaMovimiento() {
		return listaMovimiento;
	}


	public void setListaMovimiento(List<Movimiento> listaMovimiento) {
		this.listaMovimiento = listaMovimiento;
	}


	public String getContrasena() {
		return contrasena;
	}


	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	
}
