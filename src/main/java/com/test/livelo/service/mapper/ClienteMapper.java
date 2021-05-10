package com.test.livelo.service.mapper;

import com.test.livelo.persistence.dto.cliente.CadastrarClienteDTO;
import com.test.livelo.persistence.dto.cliente.EditarClienteDTO;
import com.test.livelo.persistence.enums.SexoEnum;
import com.test.livelo.persistence.modal.Cidade;
import com.test.livelo.persistence.modal.Cliente;

public class ClienteMapper {

	private ClienteMapper() {
	}

	public static void mapperEditar(Cliente cliente, EditarClienteDTO editar) {
		cliente.setNome(editar.getNome());
	}

	public static Cliente mapper(CadastrarClienteDTO cadastrar, Cidade cidade) {
		Cliente cliente = new Cliente();
		cliente.setDataNascimento(cadastrar.getDataNascimento());
		cliente.setIdade(cadastrar.getIdade());
		cliente.setNome(cadastrar.getNome());
		cliente.setSexo(SexoEnum.valueOf(cadastrar.getSexo()));
		cliente.setCidade(cidade);
		return cliente;
	}

}
