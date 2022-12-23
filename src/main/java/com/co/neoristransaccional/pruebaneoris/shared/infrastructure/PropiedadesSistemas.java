package com.co.neoristransaccional.pruebaneoris.shared.infrastructure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "mensajes-sistema")
public class PropiedadesSistemas {

	public Excepciones excepciones;
	
	public Excepciones getExcepciones() {
		return excepciones;
	}

	public void setExcepciones(Excepciones excepciones) {
		this.excepciones = excepciones;
	}

	public static class Excepciones {
		
		public Cliente cliente;
		public Cuenta cuenta;
		public Movimiento movimiento;
		
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

		public Movimiento getMovimiento() {
			return movimiento;
		}

		public void setMovimiento(Movimiento movimiento) {
			this.movimiento = movimiento;
		}

		public static class Cliente {
			public String errorEntidadNoEncontrada;
			public String generoNoEncontrado;
			public String estadoNoValido;

			public String getEstadoNoValido() {
				return estadoNoValido;
			}

			public void setEstadoNoValido(String estadoNoValido) {
				this.estadoNoValido = estadoNoValido;
			}

			public String getGeneroNoEncontrado() {
				return generoNoEncontrado;
			}

			public void setGeneroNoEncontrado(String generoNoEncontrado) {
				this.generoNoEncontrado = generoNoEncontrado;
			}

			public String getErrorEntidadNoEncontrada() {
				return errorEntidadNoEncontrada;
			}

			public void setErrorEntidadNoEncontrada(String errorEntidadNoEncontrada) {
				this.errorEntidadNoEncontrada = errorEntidadNoEncontrada;
			}
			
		}	
		
		public static class Cuenta {
			public String errorEntidadNoEncontrada;
			public String tipoCuentaNoEncontrada;
			public String tipoCuentaErrorIntegridad;

			public String getErrorEntidadNoEncontrada() {
				return errorEntidadNoEncontrada;
			}

			public void setErrorEntidadNoEncontrada(String errorEntidadNoEncontrada) {
				this.errorEntidadNoEncontrada = errorEntidadNoEncontrada;
			}

			public String getTipoCuentaNoEncontrada() {
				return tipoCuentaNoEncontrada;
			}

			public void setTipoCuentaNoEncontrada(String tipoCuentaNoEncontrada) {
				this.tipoCuentaNoEncontrada = tipoCuentaNoEncontrada;
			}

			public String getTipoCuentaErrorIntegridad() {
				return tipoCuentaErrorIntegridad;
			}

			public void setTipoCuentaErrorIntegridad(String tipoCuentaErrorIntegridad) {
				this.tipoCuentaErrorIntegridad = tipoCuentaErrorIntegridad;
			}
			
		}	
		
		public static class Movimiento {
			public String errorEntidadNoEncontrada;
			public String tipoMovimientoNoEncontrado;
			public String movimientoNoDisponibleSinSaldo;
			public String movimientoReporteSinDatos;

			public String getMovimientoReporteSinDatos() {
				return movimientoReporteSinDatos;
			}

			public void setMovimientoReporteSinDatos(String movimientoReporteSinDatos) {
				this.movimientoReporteSinDatos = movimientoReporteSinDatos;
			}

			public String getMovimientoNoDisponibleSinSaldo() {
				return movimientoNoDisponibleSinSaldo;
			}

			public void setMovimientoNoDisponibleSinSaldo(String movimientoNoDisponibleSinSaldo) {
				this.movimientoNoDisponibleSinSaldo = movimientoNoDisponibleSinSaldo;
			}

			public String getTipoMovimientoNoEncontrado() {
				return tipoMovimientoNoEncontrado;
			}

			public void setTipoMovimientoNoEncontrado(String tipoMovimientoNoEncontrado) {
				this.tipoMovimientoNoEncontrado = tipoMovimientoNoEncontrado;
			}

			public String getErrorEntidadNoEncontrada() {
				return errorEntidadNoEncontrada;
			}

			public void setErrorEntidadNoEncontrada(String errorEntidadNoEncontrada) {
				this.errorEntidadNoEncontrada = errorEntidadNoEncontrada;
			}
			
		}
		
	}
}
