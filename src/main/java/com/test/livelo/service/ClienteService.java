package com.test.livelo.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.livelo.persistence.dto.cliente.CadastrarClienteDTO;
import com.test.livelo.persistence.dto.cliente.EditarClienteDTO;
import com.test.livelo.persistence.model.Cidade;
import com.test.livelo.persistence.model.Cliente;
import com.test.livelo.persistence.repository.ClienteRepository;
import com.test.livelo.service.ConstantsUtil.ConstantsUtil;
import com.test.livelo.service.exception.NegocioException;
import com.test.livelo.service.exception.NotFoundException;
import com.test.livelo.service.mapper.ClienteMapper;

@Service
@Transactional
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private CidadeService cidadeService;

	public Optional<Cliente> buscarPorNome(String nome) {
		Optional<Cliente> cliente = clienteRepository.findByNome(nome);
		if (!cliente.isPresent()) {
			throw new NotFoundException(ConstantsUtil.MSG_BUSCA_NAO_ENCONTRADO);
		}

		return cliente;
	}

	public Cliente buscarPorId(Long id) {
		return this.clienteRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(ConstantsUtil.MSG_CLIENTE_NAO_ENCONTRADO));
	}

	public void remover(Long id) {
		if (id == null) {
			throw new NegocioException(ConstantsUtil.ERRO_REMOVER_CLIENTE);
		}
		clienteRepository.deleteById(id);

	}

	public Cliente atualizar(Long id, EditarClienteDTO editar) {
		Cliente cliente = this.clienteRepository.getOne(id);
		if (cliente.getId() == null) {
			throw new NegocioException(ConstantsUtil.MSG_CLIENTE_NAO_ENCONTRADO);
		}
		ClienteMapper.mapperEditar(cliente, editar);
		return clienteRepository.save(cliente);
	}

	public Cliente cadastrar(@Valid CadastrarClienteDTO cadastrar) {
		Optional<Cidade> id = this.cidadeService.findById(cadastrar.getCidadeId());
		if (!id.isPresent()) {
			throw new NegocioException(ConstantsUtil.MSG_CIDADE_NAO_ENCONTRADO_ID);
		}
		Cliente cliente = ClienteMapper.mapper(cadastrar, id.get());
		return clienteRepository.save(cliente);
	}

}
