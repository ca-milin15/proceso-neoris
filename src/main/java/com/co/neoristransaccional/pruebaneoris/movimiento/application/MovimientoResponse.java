package com.co.neoristransaccional.pruebaneoris.movimiento.application;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

import com.co.neoristransaccional.pruebaneoris.movimiento.domain.Movimiento;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovimientoResponse implements Serializable{

	private static final long serialVersionUID = -799171362912176987L;

	BigInteger idMovimiento;
	Double valor;
	String tipoMovimiento;
	LocalDateTime fechaMovimiento;
	
	public MovimientoResponse(Movimiento movGuardado) {
		this.idMovimiento = movGuardado.getId();
		this.valor = movGuardado.getValor();
		this.tipoMovimiento = movGuardado.getTipoMovimiento().getDescripcion();
		this.fechaMovimiento = movGuardado.getFecha();
	}

	public BigInteger getIdMovimiento() {
		return idMovimiento;
	}

	public void setIdMovimiento(BigInteger idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public LocalDateTime getFechaMovimiento() {
		return fechaMovimiento;
	}

	public void setFechaMovimiento(LocalDateTime fechaMovimiento) {
		this.fechaMovimiento = fechaMovimiento;
	}

}
