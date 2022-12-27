package com.co.neoristransaccional.pruebaneoris.cliente.application;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigInteger;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.co.neoristransaccional.pruebaneoris.cliente.domain.Cliente;
import com.co.neoristransaccional.pruebaneoris.cliente.domain.ClienteServiceRepository;
import com.co.neoristransaccional.pruebaneoris.cliente.domain.exceptions.ClienteNotFoundRuntimeException;
import com.co.neoristransaccional.pruebaneoris.cuenta.domain.exceptions.CuentaIntegrityErrorRuntimeException;
import com.co.neoristransaccional.pruebaneoris.persona.domain.exception.GeneroEnumNotFoundRuntimeException;
import com.co.neoristransaccional.pruebaneoris.shared.application.EstadoEnum;
import com.co.neoristransaccional.pruebaneoris.shared.infrastructure.PropiedadesSistemas;

@SpringBootTest(classes = {ClienteServiceUnitTest.class, PropiedadesSistemas.class})
public class ClienteServiceUnitTest {

	@Mock
	ClienteService clienteService;
	ClienteServiceRepository clienteServiceRepositoryMock;
	
	@Autowired
	PropiedadesSistemas propiedadesSistemas;
	
	@BeforeEach
	void init () {
		clienteServiceRepositoryMock = Mockito.mock(ClienteServiceRepository.class);
		clienteService = new ClienteServiceImpl(clienteServiceRepositoryMock, propiedadesSistemas);
	}
	
	@Test
	void crearCliente() {
		var clienteReq = crearObjetoClienteCrear();
		Mockito.when(clienteServiceRepositoryMock.save(any(com.co.neoristransaccional.pruebaneoris.cliente.domain.Cliente.class))).thenReturn(crearObjetoCliente());
		clienteService.crear(clienteReq);
		Mockito.verify(clienteServiceRepositoryMock, Mockito.times(1)).save(any(Cliente.class));
	}
	
	@Test
	void intentarCrearClienteConGeneroErrado() {
		assertThatThrownBy(() -> clienteService.crear(crearObjetoClienteCrearGeneroErrado())).isInstanceOf(GeneroEnumNotFoundRuntimeException.class);
	}
	
	@Test
	void actualizarCliente() {
		var clienteReq = crearObjetoClienteCrear();
		Mockito.when(clienteServiceRepositoryMock.findById(any(BigInteger.class))).thenReturn(crearObjetoClienteOptional());
		Mockito.when(clienteServiceRepositoryMock.save(any(com.co.neoristransaccional.pruebaneoris.cliente.domain.Cliente.class))).thenReturn(crearObjetoCliente());
		clienteService.actualizar(BigInteger.valueOf(1L), clienteReq);
		verify(clienteServiceRepositoryMock, times(1)).findById(any(BigInteger.class));
	}
	
	@Test
	void confirmarLanzadoExcepcionCorrectaCuandoNoExistaCliente() {
		Mockito.when(clienteServiceRepositoryMock.findById(any(BigInteger.class))).thenThrow(ClienteNotFoundRuntimeException.class);
		Mockito.when(clienteServiceRepositoryMock.save(any(com.co.neoristransaccional.pruebaneoris.cliente.domain.Cliente.class))).thenReturn(crearObjetoCliente());
		assertThatThrownBy(() -> clienteService.actualizar(BigInteger.valueOf(1L), crearObjetoClienteActualizarEstadoErrado())).isInstanceOf(ClienteNotFoundRuntimeException.class);
	}
	
	@Test
	void eliminarConfirmarLanzadoExcepcionCorrectaCuandoExisteErrorIntegridad() {
		doThrow(new CuentaIntegrityErrorRuntimeException("")).when(clienteServiceRepositoryMock).delete(any(Cliente.class));
		Mockito.when(clienteServiceRepositoryMock.findById(any(BigInteger.class))).thenReturn(crearObjetoClienteOptional());
		assertThatThrownBy(() -> clienteService.eliminar(BigInteger.valueOf(1L))).isInstanceOf(CuentaIntegrityErrorRuntimeException.class);
	}
	
	@Test
	void eliminarConfirmarLanzadoExcepcionCorrectaCuandoExisteCliente() {
		doThrow(new CuentaIntegrityErrorRuntimeException("")).when(clienteServiceRepositoryMock).delete(any(Cliente.class));
		assertThatThrownBy(() -> clienteService.eliminar(BigInteger.valueOf(1L))).isInstanceOf(ClienteNotFoundRuntimeException.class);
	}
	
	private com.co.neoristransaccional.pruebaneoris.cliente.domain.Cliente crearObjetoCliente() {
		return new com.co.neoristransaccional.pruebaneoris.cliente.domain.Cliente("Camilo", EstadoEnum.ACTIVO);
	}
	private ClienteRequest crearObjetoClienteActualizarEstadoErrado() {
		return new ClienteRequest("Camilo", "z", 29,"12345","Cra 83","3016667555","123", 2000);
	}
	private ClienteRequest crearObjetoClienteCrearGeneroErrado() {
		return new ClienteRequest("Camilo", "z", 29,"12345","Cra 83","3016667555","123", 1);
	}
	
	private ClienteRequest crearObjetoClienteCrear() {
		return new ClienteRequest("Camilo", "M", 29,"12345","Cra 83","3016667555","123", 1);
	}

	private Optional<Cliente> crearObjetoClienteOptional() {
		return Optional.of(new Cliente("Camilo", EstadoEnum.ACTIVO));
	}
}
