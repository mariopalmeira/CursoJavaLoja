package com.mariopalmeira.cursojava.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.mariopalmeira.cursojava.domain.Cliente;
import com.mariopalmeira.cursojava.services.validation.ClienteEditaValida;

@ClienteEditaValida
public class ClienteDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Nome deve ser preechido.")
	@Length(min=5, max=120, message="Nome deve ter mais de 4 e menos de menos de 120 caracteres.")
	private String nome;
	
	@NotEmpty(message="Email deve ser preenchido")
	@Email
	private String email;
	
	public ClienteDTO() {}

	public ClienteDTO(Cliente cliente) {
		id = cliente.getId();
		nome = cliente.getNome();
		email = cliente.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
