package com.co.neoristransaccional.pruebaneoris.movimiento.application;

import java.io.Serializable;
import java.util.List;

import com.co.neoristransaccional.pruebaneoris.cuenta.application.CuentaResponse;

public class ReporteMovimiento implements Serializable{

	private static final long serialVersionUID = 4163425732862523218L;

	String nombreReporte;
	String nombreCliente;
	String identificacionCliente;
	List<CuentaResponse> listaCuentas;
}
