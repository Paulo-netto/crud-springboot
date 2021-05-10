package com.test.livelo.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.livelo.persistence.dto.CidadeDTO;
import com.test.livelo.persistence.modal.Cidade;
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
	public void cadastrar(@Valid CidadeDTO cidadeDTO) {
		cidadeRepositoy.save(CidadeMapper.mapper(cidadeDTO));
	}

	public List<Cidade> buscarPorNome(String nome) {
		if (nome == null) {
			throw new NotFoundException(ConstantsUtil.MSG_CIDADE_NAO_ENCONTRADO);
		}
		return cidadeRepositoy.findByNome(nome);
	}

	public List<Cidade> buscarPorEstado(String estado) {
		if (estado == null) {
			throw new NotFoundException(ConstantsUtil.MSG_ESTADO_NAO_ENCONTRADO);
		}
		return cidadeRepositoy.findByEstado(estado);
	}
 
	public Optional<Cidade> findById(Long id) {
		return cidadeRepositoy.findById(id);
	}
	
}
