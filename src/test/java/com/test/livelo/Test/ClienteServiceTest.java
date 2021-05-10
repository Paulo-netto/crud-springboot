package com.test.livelo.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.test.livelo.persistence.dto.cliente.CadastrarClienteDTO;
import com.test.livelo.persistence.dto.cliente.EditarClienteDTO;
import com.test.livelo.persistence.model.Cidade;
import com.test.livelo.persistence.model.Cliente;
import com.test.livelo.persistence.repository.ClienteRepository;
import com.test.livelo.service.CidadeService;
import com.test.livelo.service.ClienteService;
import com.test.livelo.service.exception.NegocioException;
import com.test.livelo.service.exception.NotFoundException;

@SpringBootTest
class ClienteServiceTest {

	private static final String STRING_TEST = "test";

	private static final Long ID_TEST = 1L;

	private static final int INT_TEST = 1;

	private static final String ENUM_TEST = "M";

	@Mock
	private ClienteRepository clienteRepository;

	@Mock
	private CidadeService cidadeService;

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

	@Test
	void cadastrarTest() {
		Mockito.when(cidadeService.findById(Mockito.anyLong())).thenReturn(getCidadeOptional());
		Mockito.when(clienteRepository.save(Mockito.any(Cliente.class))).thenReturn(new Cliente());
		assertNotNull(clienteService.cadastrar(mockCadastrarClienteDTO()));
	}

	@Test
	void cadastrarNegocioExceptionTest() {
		Mockito.when(cidadeService.findById(null)).thenReturn(getCidadeOptional());
		assertThrows(NegocioException.class, () -> clienteService.cadastrar(mockCadastrarClienteDTO()));
	}

	@Test
	void atualizarTest() {
		Mockito.when(clienteRepository.getOne(Mockito.anyLong())).thenReturn(new Cliente());
		Mockito.when(clienteRepository.save(Mockito.any(Cliente.class))).thenReturn(new Cliente());
		assertNotNull(clienteService.atualizar(Mockito.anyLong(), mockEditarClienteDTO()));
	}

	@Test
	void atualizarNegocioExceptionTest() {
		Mockito.when(clienteRepository.getOne(null)).thenReturn(new Cliente());
		assertThrows(NegocioException.class, () -> clienteService.atualizar(Mockito.anyLong(), mockEditarClienteDTO()));
	}

	private Optional<Cliente> mockOptionalCliente() {
		Cliente cliente = new Cliente();
		cliente.setNome(STRING_TEST);
		return Optional.of(cliente);
	}

	private CadastrarClienteDTO mockCadastrarClienteDTO() {
		CadastrarClienteDTO dto = new CadastrarClienteDTO();
		dto.setCidadeId(mockCidade().getId());
		dto.setDataNascimento(STRING_TEST);
		dto.setIdade(INT_TEST);
		dto.setNome(STRING_TEST);
		dto.setSexo(ENUM_TEST);
		return dto;
	}

	private EditarClienteDTO mockEditarClienteDTO() {
		EditarClienteDTO dto = new EditarClienteDTO();
		dto.setNome(STRING_TEST);
		return dto;
	}

	private Cidade mockCidade() {
		Cidade cidade = new Cidade();
		cidade.setId(ID_TEST);
		return cidade;
	}

	private Optional<Cidade> getCidadeOptional() {
		Cidade cidade = new Cidade();
		cidade.setId(ID_TEST);
		return Optional.of(cidade);
	}
}
