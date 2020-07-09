package com.mariopalmeira.cursojava.configuracao;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.mariopalmeira.cursojava.services.DBService;

@Configuration
@Profile("Teste")
public class TestConfig {

	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instantiateTestDatabase() throws ParseException {
		dbService.createTestDatabase();
		return true;
	}
}
