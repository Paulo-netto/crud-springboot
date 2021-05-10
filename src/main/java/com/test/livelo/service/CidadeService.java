package com.test.livelo.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.livelo.persistence.dto.cidade.CidadeDTO;
import com.test.livelo.persistence.model.Cidade;
import com.test.livelo.persistence.repository.CidadeRepositoy;
import com.test.livelo.service.ConstantsUtil.ConstantsUtil;
import com.test.livelo.service.exception.NotFoundException;
import com.test.livelo.service.mapper.CidadeMapper;

@Service
@Transactional
public class CidadeService {

	@Autowired
	private CidadeRepositoy cidadeRepositoy;

	/**
	 * Cadastra uma Cidade
	 * 
	 * @param cidadeDTO
	 */
	public Cidade cadastrar(@Valid CidadeDTO cidadeDTO) {
		return cidadeRepositoy.save(CidadeMapper.mapper(cidadeDTO));
	}

	public List<Cidade> buscarPorNome(String nome) {
		List<Cidade> list = cidadeRepositoy.findByNome(nome);
		validacaoBusca(list);
		return list;
	}

	public List<Cidade> buscarPorEstado(String estado) {
		List<Cidade> list = cidadeRepositoy.findByEstado(estado);
		validacaoBusca(list);
		return list;
	}

	public Optional<Cidade> findById(Long id) {
		return cidadeRepositoy.findById(id);
	}

	private void validacaoBusca(List<Cidade> list) {
		if (list.isEmpty()) {
			throw new NotFoundException(ConstantsUtil.MSG_BUSCA_NAO_ENCONTRADO);
		}
	}
}
