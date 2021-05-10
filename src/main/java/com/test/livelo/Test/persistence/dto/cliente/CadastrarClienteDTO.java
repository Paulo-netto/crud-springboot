package com.test.livelo.Test.persistence.dto.cliente;

public class CadastrarClienteDTO extends ClienteDTO {

	private static final long serialVersionUID = 2262455974859940978L;

	private Long cidadeId;

	public Long getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Long cidadeId) {
		this.cidadeId = cidadeId;
	}

}
