package com.mariopalmeira.cursojava.domain.enums;

public enum EstadoPagamento {

	PENDENTE(1),
	QUITADO(2),
	CANCELADO(3);
	
	private Integer id;
	private String descricao;
	
	private EstadoPagamento(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static EstadoPagamento toEnum(Integer id) {
		if(id == null) {
			return null;
		}
		
		for(EstadoPagamento x : EstadoPagamento.values()) {
			if(id.equals(x.getId())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Estado do pagamento inválido.");
	}	
}
