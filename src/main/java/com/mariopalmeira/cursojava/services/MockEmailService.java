package com.mariopalmeira.cursojava.services;

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
}
