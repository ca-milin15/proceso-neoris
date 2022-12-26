package com.co.neoristransaccional.pruebaneoris.cuenta.domain;

import java.math.BigInteger;
import java.util.List;

import com.co.neoristransaccional.pruebaneoris.cliente.domain.Cliente;
import com.co.neoristransaccional.pruebaneoris.cuenta.application.ConverterTipoCuenta;
import com.co.neoristransaccional.pruebaneoris.cuenta.application.CuentaRequest;
import com.co.neoristransaccional.pruebaneoris.cuenta.application.TipoCuentaEnum;
import com.co.neoristransaccional.pruebaneoris.movimiento.domain.Movimiento;
import com.co.neoristransaccional.pruebaneoris.shared.application.ConverterEstadoEnum;
import com.co.neoristransaccional.pruebaneoris.shared.application.EstadoEnum;
import com.co.neoristransaccional.pruebaneoris.shared.domain.EntidadGeneral;
import com.co.neoristransaccional.pruebaneoris.shared.infrastructure.PropiedadesSistemas;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "cuenta")
public class Cuenta extends EntidadGeneral {

	private static final long serialVersionUID = -5157897178717574325L;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idCliente")
	Cliente cliente;
	

	@OneToMany
	@JoinColumn(name="idCuenta")
	List<Movimiento> listaMovimientos;

	@Column(name = "saldo")
	Double saldo;
	
	@Column(name = "saldoInicial")
	Double saldoInicial;
	
	@Convert(converter = ConverterEstadoEnum.class)
	@Column(name = "estado")
	EstadoEnum estado;
	
	@Column(name = "numeroCuenta")
	BigInteger numeroCuenta;
	
	@Convert(converter = ConverterTipoCuenta.class)
	@Column(name = "tipoCuenta")
	TipoCuentaEnum tipoCuenta;

	
	public Cuenta() {
		super();
	}

	public Cuenta(CuentaRequest cuentaRequest, Cliente cliente2, PropiedadesSistemas propiedadesSistemas) {
		this.cliente = cliente2;
		this.saldoInicial = 0D;
		this.saldo = 0D;
		this.estado = EstadoEnum.ACTIVO;
		this.numeroCuenta = cuentaRequest.getNumeroCuenta();
		this.tipoCuenta = TipoCuentaEnum.buscarEnumPorCodigo(cuentaRequest.getTipoCuenta(), propiedadesSistemas.getExcepciones().getCuenta().tipoCuentaNoEncontrada);
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Movimiento> getListaMovimientos() {
		return listaMovimientos;
	}

	public void setListaMovimientos(List<Movimiento> listaMovimientos) {
		this.listaMovimientos = listaMovimientos;
	}

	public Double getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(Double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public EstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}

	public BigInteger getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(BigInteger numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public TipoCuentaEnum getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(TipoCuentaEnum tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	
	
}
