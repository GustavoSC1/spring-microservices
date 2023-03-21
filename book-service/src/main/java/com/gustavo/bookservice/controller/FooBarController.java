package com.gustavo.bookservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("book-service")
public class FooBarController {
	
	private Logger logger = LoggerFactory.getLogger(FooBarController.class);
		
	// Repite as chamadas sempre que elas falharem, durante uma 'x' quantidade de vezes e um intervalo 'y' de tempo.
	// O mecanismo do método de fallback funciona como um bloco try/catch. Se um método de fallback for configurado, 
	// cada exceção será encaminhada para um executor de método de fallback.
	// @Retry(name = "foo-bar", fallbackMethod = "fallbackMethod")
	//
	// https://reflectoring.io/circuitbreaker-with-resilience4j
	// Um padrão comum ao usar Circuit Breaker é especificar um método de fallback a ser chamado quando o circuito estiver aberto.
	//@CircuitBreaker(name = "default", fallbackMethod = "fallbackMethod")
	//
	// Essa funcionalidade permite limitar o acesso a algum serviço. Determina a quantidade de chamadas que você pode fazer para 
	// um end-point. Ex: Em x segundos eu posso receber y requisições
	//@RateLimiter(name = "default")
	@GetMapping("/foo-bar")
	// Possibilita limitar o número de chamadas simultâneas para um determinado serviço.
	@Bulkhead(name = "default")
	public String fooBar() {
		logger.info("Request to foo-bar is received!");
		/*
		var response = new RestTemplate()
				.getForEntity("http://localhost:8080/foo-bar", String.class); // Faz uma solicitação pra um endereço inexistente
		 */
		 return "Foo-Bar!!!";
		//return response.getBody();
	}
	
	public String fallbackMethod(Exception ex) {
		return "fallbackMethod foo-bar!!!";
	}

}
