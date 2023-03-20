package com.gustavo.bookservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("book-service")
public class FooBarController {
	
	private Logger logger = LoggerFactory.getLogger(FooBarController.class);
	
	@GetMapping("/foo-bar")
	// Repite as chamadas sempre que elas falharem, durante uma 'x' quantidade de vezes e um intervalo 'y' de tempo.
	// O mecanismo do método de fallback funciona como um bloco try/catch. Se um método de fallback for configurado, 
	// cada exceção será encaminhada para um executor de método de fallback.
	@Retry(name = "foo-bar", fallbackMethod = "fallbackMethod")
	public String fooBar() {
		logger.info("Request to foo-bar is received!");
		var response = new RestTemplate()
				.getForEntity("http://localhost:8080/foo-bar", String.class); // Faz uma solicitação pra um endereço inexistente
		// return "Foo-Bar!!!";
		return response.getBody();		
	}
	
	public String fallbackMethod(Exception ex) {
		return "fallbackMethod foo-bar!!!";
	}

}
