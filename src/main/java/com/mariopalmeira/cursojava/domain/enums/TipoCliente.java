package com.mariopalmeira.cursojava.domain.enums;

public enum TipoCliente {
	
	//Valores setados manualmente, mas o JPA faria automáticamente
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private Integer id; 
	private String descricao;
	
	//Construtor de Enum é sempre private
	private TipoCliente(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	//Enums só tem getters
	public Integer getId() {
		return id;
	}
	
	public String getDescricao() {
		return descricao;
	}	
	
	//Método tipo static para que possa ser usado independente da instância do objeto
	public static TipoCliente toEnum(Integer id) {
		if(id == null) {
			return null;
		}
		
		//Foreach do java
		//Pra cada objeto da classe TipoCliente, as x, dentre os possíveis 
		for(TipoCliente x : TipoCliente.values()) {
			if(id.equals(x.getId())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Tipo de cliente inválido.");
	}
}
