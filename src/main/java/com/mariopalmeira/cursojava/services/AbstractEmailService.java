package com.mariopalmeira.cursojava.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.mariopalmeira.cursojava.domain.Pedido;

public abstract class AbstractEmailService implements EmailService{

	@Value("${default.sender}")
	private String enviaPara;
	
	@Override
	public void enviaConfirmacaoPedido(Pedido pedido) {
		SimpleMailMessage smm = preparaCorpoEmail(pedido); 
		enviaEmail(smm);
	}
	
	protected SimpleMailMessage preparaCorpoEmail(Pedido pedido) {
		SimpleMailMessage corpo = new SimpleMailMessage();
		corpo.setTo(pedido.getCliente().getEmail());
		corpo.setFrom(enviaPara);
		corpo.setSubject("Pedido "+pedido.getId()+" recebido!");
		corpo.setSentDate(new Date());
		corpo.setText(pedido.toString());
		return corpo;
	}
}
