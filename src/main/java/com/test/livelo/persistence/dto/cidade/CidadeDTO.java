package com.test.livelo.persistence.dto.cidade;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CidadeDTO implements Serializable {

	private static final long serialVersionUID = 5335526086053845197L;

	@NotEmpty(message = "Nome da cidade não pode ser vazio")
	@NotNull
	private String cidade;

	@NotEmpty(message = "Nome do estado não pode ser vazio")
	@NotNull
	private String estado;

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
