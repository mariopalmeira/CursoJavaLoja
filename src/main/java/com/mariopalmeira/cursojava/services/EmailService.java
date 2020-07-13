package com.mariopalmeira.cursojava.services;

import org.springframework.mail.SimpleMailMessage;

import com.mariopalmeira.cursojava.domain.Pedido;

public interface EmailService {
	
	void enviaConfirmacaoPedido(Pedido pedido);
	
	void enviaEmail(SimpleMailMessage msg);
}
