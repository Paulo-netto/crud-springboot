package com.test.livelo.Test.service.mapper;

import org.springframework.stereotype.Component;

import com.test.livelo.Test.persistence.dto.CidadeDTO;
import com.test.livelo.Test.persistence.modal.Cidade;

@Component
public class CidadeMapper {

	private CidadeMapper() {
	}

	public static Cidade mapper(CidadeDTO cidadeDto) {
		Cidade cidade = new Cidade();
		cidade.setEstado(cidadeDto.getEstado());
		cidade.setNome(cidadeDto.getNome());
		return cidade;
	}
 
	public static CidadeDTO mapper(Cidade cidade) {
		CidadeDTO dto = new CidadeDTO();
		dto.setEstado(cidade.getEstado());
		dto.setNome(cidade.getNome());
		return dto;
	}
}
