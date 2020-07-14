package com.mariopalmeira.cursojava.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.mariopalmeira.cursojava.domain.Pedido;

public abstract class AbstractEmailService implements EmailService{

	@Value("${default.sender}")
	private String enviaPara;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
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
	
	protected String htmlEmTemplatePedido(Pedido pedido) {
		Context context = new Context();
		context.setVariable("pedido", pedido);
		return templateEngine.process("email/confirmacaoPedido", context);
	}
	
	@Override
	public void enviaConfirmacaoPedidoHtml(Pedido pedido) {
		try {
			MimeMessage mmsg;
			mmsg = preparaCorpoEmailHtml(pedido);
			enviaEmailHtml(mmsg);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	private MimeMessage preparaCorpoEmailHtml(Pedido pedido) throws MessagingException {
		MimeMessage mmsg = javaMailSender.createMimeMessage();
		MimeMessageHelper mmhelper = new MimeMessageHelper(mmsg, true);
		mmhelper.setTo(pedido.getCliente().getEmail());
		mmhelper.setFrom(enviaPara);
		mmhelper.setSubject("Pedido "+pedido.getId()+" recebido!");
		mmhelper.setSentDate(new Date());
		mmhelper.setText(htmlEmTemplatePedido(pedido), true);
		return mmsg;
	}
	
}
