package com.test.livelo.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.test.livelo.persistence.dto.cidade.CidadeDTO;
import com.test.livelo.persistence.model.Cidade;
import com.test.livelo.persistence.repository.CidadeRepositoy;
import com.test.livelo.service.CidadeService;
import com.test.livelo.service.exception.NotFoundException;

@SpringBootTest
class CidadeServiceTest {

	private static final String STRING_TEST = "test";

	@Mock
	private CidadeRepositoy cidadeRepositoy;

	@InjectMocks
	private CidadeService cidadeService;

	@Test
	void buscarPorNomeTest() {
		Mockito.when(cidadeRepositoy.findByNome(Mockito.anyString())).thenReturn(mockListCidadeDTO());
		assertNotNull(cidadeService.buscarPorNome(Mockito.anyString()));
	}

	@Test
	void buscarPorEstadoTest() {
		Mockito.when(cidadeRepositoy.findByEstado(Mockito.anyString())).thenReturn(mockListCidadeDTO());
		assertNotNull(cidadeService.buscarPorEstado(Mockito.anyString()));
	}

	@Test
	void buscarPorNomeTestNotFoundException() {
		Mockito.when(cidadeRepositoy.findByNome(Mockito.anyString())).thenReturn(new ArrayList<>());
		assertThrows(NotFoundException.class, () -> cidadeService.buscarPorNome(null));
	}

	@Test
	void buscarPorEstadoTestNotFoundException() {
		Mockito.when(cidadeRepositoy.findByEstado(Mockito.anyString())).thenReturn(new ArrayList<>());
		assertThrows(NotFoundException.class, () -> cidadeService.buscarPorEstado(null));
	}

	@Test
	void cadastrarTest() {
		Mockito.when(cidadeRepositoy.save(Mockito.any(Cidade.class))).thenReturn(new Cidade());
		assertNotNull(cidadeService.cadastrar(mockCidadeDTO()));
	}

	@Test
	void buscarPorIdTest() {
		Mockito.when(cidadeRepositoy.findById(Mockito.anyLong())).thenReturn(getCidadeOptional());
		assertNotNull(cidadeService.findById(Mockito.anyLong()));
	}

	private CidadeDTO mockCidadeDTO() {
		CidadeDTO dto = new CidadeDTO();
		dto.setCidade(STRING_TEST);
		dto.setEstado(STRING_TEST);
		return dto;
	}

	private Optional<Cidade> getCidadeOptional() {
		Cidade cidade = new Cidade();
		return Optional.of(cidade);
	}

	private List<CidadeDTO> mockListCidadeDTO() {
		List<CidadeDTO> list = new ArrayList<>();
		list.add(mockCidadeDTO());
		return list;

	}

}
