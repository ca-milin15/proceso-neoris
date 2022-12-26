package com.co.neoristransaccional.pruebaneoris.cliente.application;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.co.neoristransaccional.pruebaneoris.cliente.domain.ClienteServiceRepository;
import com.co.neoristransaccional.pruebaneoris.persona.domain.exception.GeneroEnumNotFoundRuntimeException;
import com.co.neoristransaccional.pruebaneoris.shared.application.EstadoEnum;
import com.co.neoristransaccional.pruebaneoris.shared.infrastructure.PropiedadesSistemas;

@SpringBootTest
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
	//Invoca una sola vez el metodo de almacenar
	void crearCliente() {
		ClienteRequest clienteReq = crearObjetoClienteCrear();
		Mockito.when(clienteServiceRepositoryMock.save(any(com.co.neoristransaccional.pruebaneoris.cliente.domain.Cliente.class))).thenReturn(crearObjetoCliente());
		clienteService.crear(clienteReq);
		Mockito.verify(clienteServiceRepositoryMock, Mockito.times(1));
	}
	
	@Test
	//Invoca una sola vez el metodo de almacenar
	void intentarCrearClienteConGeneroErrado() {
		assertThatThrownBy(() -> clienteService.crear(crearObjetoClienteCrearGeneroErrado())).isInstanceOf(GeneroEnumNotFoundRuntimeException.class);
	}
	
	private com.co.neoristransaccional.pruebaneoris.cliente.domain.Cliente crearObjetoCliente() {
		return new com.co.neoristransaccional.pruebaneoris.cliente.domain.Cliente("Camilo", EstadoEnum.ACTIVO);
	}

	private ClienteRequest crearObjetoClienteCrearGeneroErrado() {
		return new ClienteRequest("Camilo", "z", 29,"12345","Cra 83","3016667555","123");
	}
	
	private ClienteRequest crearObjetoClienteCrear() {
		return new ClienteRequest("Camilo", "M", 29,"12345","Cra 83","3016667555","123");
	}

}
