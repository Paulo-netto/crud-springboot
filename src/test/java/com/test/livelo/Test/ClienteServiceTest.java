package com.test.livelo.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.test.livelo.Test.persistence.modal.Cliente;
import com.test.livelo.Test.persistence.repository.ClienteRepository;
import com.test.livelo.Test.service.ClienteService;
import com.test.livelo.Test.service.exception.NotFoundException;

@SpringBootTest
class ClienteServiceTest {


	private static final String STRING_TEST = "test";

	@Mock
	private ClienteRepository clienteRepository;

	@InjectMocks
	private ClienteService clienteService;
	
	@Test
	void buscarPorNomeTest() {
		Mockito.when(clienteRepository.findByNome(Mockito.anyString())).thenReturn(mockOptionalCliente());
		assertNotNull(clienteService.buscarPorNome(Mockito.anyString()));
	}
	
	@Test
	void buscarPorIdTest() {
		Mockito.when(clienteRepository.findById(Mockito.anyLong())).thenReturn(mockOptionalCliente());
		assertNotNull(clienteService.buscarPorId(Mockito.anyLong()));
	}
	
	
	@Test
	void buscarPorIdTestNotFoundException() {
		Mockito.when(clienteRepository.findById(Mockito.anyLong())).thenReturn(mockOptionalCliente());
		assertThrows(NotFoundException.class, () -> clienteService.buscarPorId(null));
	}

	
	private Optional<Cliente> mockOptionalCliente(){
		Cliente cliente = new Cliente();
		cliente.setNome(STRING_TEST);
		return Optional.of(cliente);
	}
}
