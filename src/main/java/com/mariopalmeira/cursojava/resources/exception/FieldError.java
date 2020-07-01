package com.mariopalmeira.cursojava.resources.exception;

import java.io.Serializable;

public class FieldError implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String nomeCampo;
	private String messagem;
	
	public FieldError() {}

	public FieldError(String nomeCampo, String messagem) {
		super();
		this.nomeCampo = nomeCampo;
		this.messagem = messagem;
	}

	public String getNomeCampo() {
		return nomeCampo;
	}

	public void setNomeCampo(String nomeCampo) {
		this.nomeCampo = nomeCampo;
	}

	public String getMessagem() {
		return messagem;
	}

	public void setMessagem(String messagem) {
		this.messagem = messagem;
	}
}
