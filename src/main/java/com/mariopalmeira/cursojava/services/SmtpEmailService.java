package com.mariopalmeira.cursojava.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class SmtpEmailService extends AbstractEmailService{
	
	private MailSender mailsender;
	
	private static final Logger Log = LoggerFactory.getLogger(SmtpEmailService.class);
	
	@Override
	public void enviaEmail(SimpleMailMessage msg){
		Log.info("***********************");
		mailsender.send(msg);
		Log.info("***********************");
	}

}
