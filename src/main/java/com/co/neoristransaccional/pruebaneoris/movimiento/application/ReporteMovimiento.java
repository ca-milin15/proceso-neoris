package com.co.neoristransaccional.pruebaneoris.movimiento.application;

import java.io.Serializable;
import java.util.List;

public class ReporteMovimiento implements Serializable{

	private static final long serialVersionUID = 4163425732862523218L;

	String nombreReporte;
	String nombreCliente;
	String identificacionCliente;
	List<CuentaMovimientosResponse> listaCuentas;
	
	public String getNombreReporte() {
		return nombreReporte;
	}
	public void setNombreReporte(String nombreReporte) {
		this.nombreReporte = nombreReporte;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getIdentificacionCliente() {
		return identificacionCliente;
	}
	public void setIdentificacionCliente(String identificacionCliente) {
		this.identificacionCliente = identificacionCliente;
	}
	public List<CuentaMovimientosResponse> getListaCuentas() {
		return listaCuentas;
	}
	public void setListaCuentas(List<CuentaMovimientosResponse> listaCuentas) {
		this.listaCuentas = listaCuentas;
	}
	
}
