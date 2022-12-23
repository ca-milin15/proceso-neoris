package com.co.neoristransaccional.pruebaneoris.movimiento.domain;

import jakarta.persistence.Table;

import java.math.BigInteger;

import com.co.neoristransaccional.pruebaneoris.cliente.domain.Cliente;
import com.co.neoristransaccional.pruebaneoris.cuenta.domain.Cuenta;
import com.co.neoristransaccional.pruebaneoris.movimiento.application.ConverterTipoMovimiento;
import com.co.neoristransaccional.pruebaneoris.movimiento.application.MovimientoRequest;
import com.co.neoristransaccional.pruebaneoris.movimiento.application.TipoMovimientoEnum;
import com.co.neoristransaccional.pruebaneoris.shared.domain.EntidadGeneral;
import com.co.neoristransaccional.pruebaneoris.shared.infrastructure.PropiedadesSistemas;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
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
@Table(name = "movimiento")
@NoArgsConstructor
@AllArgsConstructor
public class Movimiento  extends EntidadGeneral {
	
	private static final long serialVersionUID = -5986681911066094141L;

	@ManyToOne(fetch = FetchType.LAZY)
	Cliente cliente;

	@ManyToOne(fetch = FetchType.LAZY)
	Cuenta cuenta;
	
	@Column(name = "valor")
	Double valor;
	
	@Column(name = "saldo")
	Double saldo;

	@Convert(converter = ConverterTipoMovimiento.class)
	@Column(name = "tipo_movimiento")
	TipoMovimientoEnum tipoMovimiento;
	
	public Movimiento() {
		super();
	}
	public Movimiento(BigInteger cliente2, BigInteger cuenta2, Double saldo, Double saldoInicial, String tipoMov) {
		System.out.println();
	}
	public Movimiento(Cliente cliente2, Cuenta cuenta2, MovimientoRequest movimientoRequest,
			PropiedadesSistemas propiedadesSistemas) {
		this.cliente = cliente2;
		this.cuenta = cuenta2;
		this.valor = movimientoRequest.getValor();
		this.tipoMovimiento = TipoMovimientoEnum.buscarEnumPorCodigo(movimientoRequest.getTipoMovimiento(), propiedadesSistemas.getExcepciones().getMovimiento().getTipoMovimientoNoEncontrado());
		this.saldo = cuenta2.getSaldo();
	}

	public TipoMovimientoEnum getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(TipoMovimientoEnum tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

}
