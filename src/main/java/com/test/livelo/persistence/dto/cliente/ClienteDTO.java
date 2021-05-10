package com.test.livelo.persistence.dto.cliente;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = -2168201418089461948L;

	@NotEmpty(message = "estado não pode ser vazio")
	private String nome;

	@NotEmpty(message = "estado não pode ser vazio")
	private String sexo;

	@NotEmpty(message = "estado não pode ser vazio")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private String dataNascimento;

	@NotEmpty(message = "estado não pode ser vazio")
	private int idade;

	private Long cidadeId;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public Long getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Long cidadeId) {
		this.cidadeId = cidadeId;
	}

}
