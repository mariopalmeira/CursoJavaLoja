package com.mariopalmeira.cursojava.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class SmtpEmailService extends AbstractEmailService{
	
	@Autowired
	private MailSender mailsender;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	private static final Logger Log = LoggerFactory.getLogger(SmtpEmailService.class);
	
	@Override
	public void enviaEmail(SimpleMailMessage msg){
		Log.info("***********************");
		mailsender.send(msg);
		Log.info("***********************");
	}

	@Override
	public void enviaEmailHtml(MimeMessage mmsg) {
		Log.info("***********************");
		javaMailSender.send(mmsg);
		Log.info("***********************");
	}

}
