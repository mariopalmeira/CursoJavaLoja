package com.mariopalmeira.cursojava.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class MockEmailService extends AbstractEmailService{
	
	private static final Logger Log = LoggerFactory.getLogger(MockEmailService.class);
	
	@Override
	public void enviaEmail(SimpleMailMessage msg){
		Log.info("***********************");
		Log.info(msg.toString());
		Log.info("***********************");
	}

	@Override
	public void enviaEmailHtml(MimeMessage mmsg) {
		Log.info("***********************");
		Log.info(mmsg.toString());
		Log.info("***********************");
	}
}
