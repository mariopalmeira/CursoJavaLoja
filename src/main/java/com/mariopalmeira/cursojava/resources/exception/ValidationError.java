package com.mariopalmeira.cursojava.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
	private static final long serialVersionUID = 1L;

	private List<FieldError> erros = new ArrayList<>();

	public ValidationError(Long timestamp, Integer status, String error, String mensage, String path) {
		super(timestamp, status, error, mensage, path);
	}

	public List<FieldError> getErros() {
		return erros;
	}

	public void setErros(String nomeCampo, String mensagem) {
		erros.add(new FieldError(nomeCampo, mensagem));
	}
}
