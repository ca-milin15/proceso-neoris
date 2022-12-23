package com.co.neoristransaccional.pruebaneoris.movimiento.domain;

import java.math.BigInteger;

public class MovimientoReporteDatosNativos {
	
	BigInteger cliente;

	BigInteger cuenta;
	
	Double valor;
	
	Double saldo;

	String tipoMovimiento;

	public MovimientoReporteDatosNativos(BigInteger cliente, BigInteger cuenta, Double valor, Double saldo,
			String tipoMovimiento) {
		super();
		this.cliente = cliente;
		this.cuenta = cuenta;
		this.valor = valor;
		this.saldo = saldo;
		this.tipoMovimiento = tipoMovimiento;
	}
	
	
}
