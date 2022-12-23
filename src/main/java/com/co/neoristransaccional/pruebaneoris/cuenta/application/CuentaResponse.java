package com.co.neoristransaccional.pruebaneoris.cuenta.application;

import java.io.Serializable;
import java.math.BigInteger;

import com.co.neoristransaccional.pruebaneoris.cuenta.domain.Cuenta;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CuentaResponse implements Serializable{

	private static final long serialVersionUID = -2350489265714588134L;

	BigInteger idCuenta;
	BigInteger numeroCuenta;
	String tipoCuenta;
	Double saldoInicial;
	String estado;
	
	public CuentaResponse(Cuenta cuenta) {
		this.idCuenta = cuenta.getId();
		this.numeroCuenta = cuenta.getNumeroCuenta();
		this.tipoCuenta = cuenta.getTipoCuenta().getDescripcion();
		this.saldoInicial = cuenta.getSaldoInicial();
		this.estado = cuenta.getEstado().getDescripcion();
	}


	public BigInteger getIdCuenta() {
		return idCuenta;
	}


	public void setIdCuenta(BigInteger idCuenta) {
		this.idCuenta = idCuenta;
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


	public Double getSaldoInicial() {
		return saldoInicial;
	}


	public void setSaldoInicial(Double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}
}
