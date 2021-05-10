package com.test.livelo.service.exception;

public class NotFoundException extends RuntimeException  {

	private static final long serialVersionUID = 6404546726547713850L;

	public NotFoundException(String msg) {
		super(msg);
	}

}
