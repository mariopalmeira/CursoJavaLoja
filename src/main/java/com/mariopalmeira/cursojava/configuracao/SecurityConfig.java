package com.mariopalmeira.cursojava.configuracao;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private Environment environment;
	
	//URLS Públicas
	private static final String[] LINKS_PUBLICOS = {
			"/h2-console/**"
	};
	
	private static final String[] LINKS_SOMENTE_LEITURA = {
			"/produtos/**",
			"/categorias/**"
	};	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		//Tendo perfil de teste nos perfis ativos
		if(Arrays.asList(environment.getActiveProfiles()).contains("test")) {
			//Liberar acesso
			http.headers().frameOptions().disable();
		}
		
		//Chamada do método de baixo sobre origem das requisições e desabilitar a segurança quanto ao armazenamento de informações na sessão
		//já que a aplicação não usa sessão
		http.cors().and().csrf().disable();
		//Das requisições que vierem, se forem iguais as contididas em LINKS_SOMENTE_LEITURA só permita GET, se forem iguais as que estão em LINKS_PUBLICOS, deixe passar, as demais autentique
		http.authorizeRequests().antMatchers(HttpMethod.GET, LINKS_SOMENTE_LEITURA).permitAll().antMatchers(LINKS_PUBLICOS).permitAll().anyRequest().authenticated();
		//A política de criação de sessão é sem sessão 
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	//Permite requisição de qualquer lugar
	  @Bean
	  CorsConfigurationSource corsConfigurationSource() {
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
	    return source;
	  }

}
