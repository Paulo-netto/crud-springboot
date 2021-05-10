package com.test.livelo.persistence.dto.cliente;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

public class EditarClienteDTO implements Serializable {

	private static final long serialVersionUID = -2791983955203728987L;

	@NotEmpty(message = "estado não pode ser vazio")
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
