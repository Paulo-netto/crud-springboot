package com.test.livelo.persistence.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

public class CidadeDTO implements Serializable {

	private static final long serialVersionUID = 5335526086053845197L;

	@NotEmpty(message = "nome não pode ser vazio")
	private String nome;

	@NotEmpty(message = "estado não pode ser vazio")
	private String estado;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
