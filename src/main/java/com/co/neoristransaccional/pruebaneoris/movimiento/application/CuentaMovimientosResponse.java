package com.co.neoristransaccional.pruebaneoris.movimiento.application;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

public class CuentaMovimientosResponse implements Serializable{

	private static final long serialVersionUID = -774909094601938155L;

	BigInteger numeroCuenta;
	List<MovimientoResponse> movimientos;
	
	public BigInteger getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(BigInteger numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public List<MovimientoResponse> getMovimientos() {
		return movimientos;
	}
	public void setMovimientos(List<MovimientoResponse> movimientos) {
		this.movimientos = movimientos;
	}
	
	
}
