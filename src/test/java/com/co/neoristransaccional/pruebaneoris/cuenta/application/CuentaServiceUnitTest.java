package com.co.neoristransaccional.pruebaneoris.cuenta.application;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigInteger;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.co.neoristransaccional.pruebaneoris.cliente.application.ClienteService;
import com.co.neoristransaccional.pruebaneoris.cuenta.domain.Cuenta;
import com.co.neoristransaccional.pruebaneoris.cuenta.domain.CuentaServiceRepository;
import com.co.neoristransaccional.pruebaneoris.cuenta.domain.exceptions.CuentaNotFoundRuntimeException;
import com.co.neoristransaccional.pruebaneoris.shared.application.EstadoEnum;
import com.co.neoristransaccional.pruebaneoris.shared.domain.exceptions.EstadoNotFoundRuntimeException;
import com.co.neoristransaccional.pruebaneoris.shared.infrastructure.PropiedadesSistemas;

@SpringBootTest
public class CuentaServiceUnitTest {
	
	CuentaService cuentaService;
	ClienteService clienteService;
	CuentaServiceRepository cuentaServiceRepositoryMock;
	
	@Autowired
	PropiedadesSistemas propiedadesSistemas;
	
	@BeforeEach
	void init () {
		cuentaServiceRepositoryMock = Mockito.mock(CuentaServiceRepository.class);
		clienteService = Mockito.mock(ClienteService.class);
		cuentaService = new CuentaServiceImpl(cuentaServiceRepositoryMock, propiedadesSistemas, clienteService);
	}
	
	
	@Test
	void actualizarCuenta() {
		var cuentaReq = crearObjetoCuentaCrear();
		Mockito.when(cuentaServiceRepositoryMock.findById(any(BigInteger.class))).thenReturn(crearObjetoCuentaOptional());
		Mockito.when(cuentaServiceRepositoryMock.save(any(Cuenta.class))).thenReturn(crearObjetoCuenta());
		cuentaService.actualizar(BigInteger.valueOf(1L), cuentaReq);
		verify(cuentaServiceRepositoryMock, times(1)).findById(any(BigInteger.class));
	}
	
	@Test
	void crearCuentaVerificarBusquedaClienteNoEncontrado() {
		var cuentaReq = crearObjetoTipoCuentaErroneo();
		assertThatThrownBy(() -> cuentaService.crear(cuentaReq)).isInstanceOf(EstadoNotFoundRuntimeException.class);
		
	}
	
	@Test
	void crearCuentaVerificarValidacionValoresDelEnumeradoTipoCuenta() {
		Mockito.when(clienteService.buscarPorId(any(BigInteger.class))).thenReturn(crearObjetoCliente());
		var cuentaReq = crearObjetoTipoCuentaErroneo();
		assertThatThrownBy(() -> cuentaService.crear(cuentaReq)).isInstanceOf(EstadoNotFoundRuntimeException.class);
		
	}
	
	private com.co.neoristransaccional.pruebaneoris.cliente.domain.Cliente crearObjetoCliente() {
		return new com.co.neoristransaccional.pruebaneoris.cliente.domain.Cliente("Camilo", EstadoEnum.ACTIVO);
	}
	private Cuenta crearObjetoCuenta() {
		var cuenta = new Cuenta();
		cuenta.setEstado(EstadoEnum.ACTIVO);
		cuenta.setTipoCuenta(TipoCuentaEnum.AHORROS);
		return cuenta;
	}

	private CuentaRequest crearObjetoTipoCuentaErroneo() {
		return new CuentaRequest(BigInteger.valueOf(1L), BigInteger.valueOf(112312L), "zz", 1);
	}
	
	private CuentaRequest crearObjetoCuentaCrear() {
		return new CuentaRequest(BigInteger.valueOf(1L), BigInteger.valueOf(112312L), "CA", 1);
	}
	
	private Optional<Cuenta> crearObjetoCuentaOptional() {
		return Optional.of(new Cuenta());
	}
	
	@Test
	//Verificar que al intentar eliminar una cuenta, exista previamente
	void intentarEliminarCuentaNoExistente() {
		assertThatThrownBy(() -> cuentaService.eliminar(BigInteger.valueOf(1000L))).isInstanceOf(CuentaNotFoundRuntimeException.class);
	}
	
}
