package com.co.neoristransaccional.pruebaneoris.cuenta.application;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigInteger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.co.neoristransaccional.pruebaneoris.cliente.application.ClienteService;
import com.co.neoristransaccional.pruebaneoris.cuenta.domain.CuentaServiceRepository;
import com.co.neoristransaccional.pruebaneoris.cuenta.domain.exceptions.CuentaNotFoundRuntimeException;
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
		cuentaService = new CuentaServiceImpl(cuentaServiceRepositoryMock, propiedadesSistemas, clienteService);
	}
	
	@Test
	//Verifica que el metodo eliminar del repository sea invoca y retorna true
	void eliminarCuenta() {
		assertTrue(cuentaService.eliminar(BigInteger.valueOf(1L)));
		Mockito.verify(cuentaServiceRepositoryMock, Mockito.times(10));
	}
	
	@Test
	//Verificar que al intentar eliminar una cuenta, exista previamente
	void intentarCrearClienteConGeneroErrado() {
		assertThatThrownBy(() -> cuentaService.eliminar(BigInteger.valueOf(1000L))).isInstanceOf(CuentaNotFoundRuntimeException.class);
	}
	
	private CuentaRequest crearObjetoCuentaCrear() {
		return new CuentaRequest(BigInteger.valueOf(8L), BigInteger.valueOf(23232L), "CA", 1);
	}
}
