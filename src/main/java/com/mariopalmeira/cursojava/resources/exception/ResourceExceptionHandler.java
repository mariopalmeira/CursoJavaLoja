package com.mariopalmeira.cursojava.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mariopalmeira.cursojava.services.exception.DataIntegrityException;
import com.mariopalmeira.cursojava.services.exception.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	/*
	 * O erro vai acontecer na classe de serviço. Quando acontecer, ele vai chamar a classe de exceção personalizada, passando uma mensagem de erro.
	 * O manipulador de exceções, aqui, vai receber a exceção e a requisição. Usando a classe de erro standard, passa o valor do status http, a mensagem de erro que foi
	 * dada lá na classe de serviço e o momento. Vai setar a response como 404 e no corpo o erro.*/
	//O manipulador de exceções vai retornar um response entity de standard error, usando como paâmetro a excecao do object not found do meu pacote e dados da requisição
	@ExceptionHandler(ObjectNotFoundException.class)//indica que essa classe se trata de um manipulador de exceções do OObjectNotFoundException
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException excecao, HttpServletRequest requisicao){
		//Usando uma classe de erro personalizada, do meu pacote, ele vai retornar o status http, a mensagem e o momento que a exceção ocorreu
		StandardError erro = new StandardError(HttpStatus.NOT_FOUND.value(), excecao.getMessage(), System.currentTimeMillis());
		//O status do retorno vai ser 404 e no corpo as informações da exceção
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException excecao, HttpServletRequest requisicao){
		StandardError erro = new StandardError(HttpStatus.BAD_REQUEST.value(), excecao.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
}
