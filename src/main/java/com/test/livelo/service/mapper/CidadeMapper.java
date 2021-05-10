package com.test.livelo.service.mapper;

import org.springframework.stereotype.Component;

import com.test.livelo.persistence.dto.cidade.CidadeDTO;
import com.test.livelo.persistence.model.Cidade;

@Component
public class CidadeMapper {

	private CidadeMapper() {
	}

	public static Cidade mapper(CidadeDTO cidadeDto) {
		Cidade cidade = new Cidade();
		cidade.setEstado(cidadeDto.getEstado());
		cidade.setNome(cidadeDto.getCidade());
		return cidade;
	}

}
