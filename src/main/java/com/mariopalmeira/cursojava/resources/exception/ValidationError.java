package com.mariopalmeira.cursojava.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
	private static final long serialVersionUID = 1L;

	private List<FieldError> erros = new ArrayList<>();
	
	public ValidationError(Integer status, String mensagem, Long timeStamp) {
		super(status, mensagem, timeStamp);
	}

	public List<FieldError> getErros() {
		return erros;
	}

	public void setErros(String nomeCampo, String mensagem) {
		erros.add(new FieldError(nomeCampo, mensagem));
	}
}
