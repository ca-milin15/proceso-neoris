package com.co.neoristransaccional.pruebaneoris.movimiento.application;

import java.math.BigInteger;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovimientoRequest {

	BigInteger idCliente;
	BigInteger idCuenta;
	String tipoMovimiento;
	Double valor;
	
	public MovimientoRequest() {
		super();
	}

	public BigInteger getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(BigInteger idCliente) {
		this.idCliente = idCliente;
	}

	public BigInteger getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(BigInteger idCuenta) {
		this.idCuenta = idCuenta;
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	
}
