package com.test.livelo.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.test.livelo.persistence.dto.CidadeDTO;
import com.test.livelo.persistence.repository.CidadeRepositoy;
import com.test.livelo.service.CidadeService;
import com.test.livelo.service.exception.NotFoundException;

@SpringBootTest 
class CidadeServiceTest {

	private static final String STRING_TEST = null;

	@Mock
	private CidadeRepositoy cidadeRepositoy;

	@InjectMocks
	private CidadeService cidadeService;

	@Test
	void buscarPorNomeTest() {
		Mockito.when(cidadeRepositoy.findByNome(Mockito.anyString())).thenReturn(new ArrayList<>());
		assertNotNull(cidadeService.buscarPorNome(Mockito.anyString()));
	}

	@Test
	void buscarPorEstadoTest() {
		Mockito.when(cidadeRepositoy.findByEstado(Mockito.anyString())).thenReturn(new ArrayList<>());
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

//	@Test
//	void cadastrarTest() {
//		Mockito.when(cidadeRepositoy.save(Mockito.any())).thenReturn(mockCidadeDTO());
//		cidadeService.cadastrar(mockCidadeDTO());
//	}

	private CidadeDTO mockCidadeDTO() {
		CidadeDTO dto = new CidadeDTO();
		dto.setNome(STRING_TEST);
		dto.setEstado(STRING_TEST);
		return dto;
	}

}
