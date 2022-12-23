package com.co.neoristransaccional.pruebaneoris.cuenta.application;

import java.io.Serializable;
import java.math.BigInteger;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CuentaRequest implements Serializable{

	private static final long serialVersionUID = 2627641852588728490L;

	BigInteger idCliente;
	BigInteger numeroCuenta;
	String tipoCuenta;
	Integer estado;
	
	public CuentaRequest() {
		super();
	}
	
	public CuentaRequest(BigInteger idCliente, BigInteger numeroCuenta, String tipoCuenta, Integer estado) {
		super();
		this.idCliente = idCliente;
		this.numeroCuenta = numeroCuenta;
		this.tipoCuenta = tipoCuenta;
		this.estado = estado;
	}

	public BigInteger getIdCliente() {
		return idCliente;
	}
	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public void setIdCliente(BigInteger idCliente) {
		this.idCliente = idCliente;
	}
	public BigInteger getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(BigInteger numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public String getTipoCuenta() {
		return tipoCuenta;
	}
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
}
