package com.mariopalmeira.cursojava.services.exception;

public class AuthorizationException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public AuthorizationException(String mensagem) {
		super(mensagem);
	}
	
	public AuthorizationException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}	
}
