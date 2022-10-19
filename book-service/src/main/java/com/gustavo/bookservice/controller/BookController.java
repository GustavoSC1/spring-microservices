package com.gustavo.bookservice.controller;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.gustavo.bookservice.model.Book;
import com.gustavo.bookservice.repository.BookRepository;
import com.gustavo.bookservice.response.Cambio;

@RestController
@RequestMapping("book-service")
public class BookController {
	
	@Autowired
	private Environment enviroment;
	
	@Autowired
	private BookRepository repository;
	
	@GetMapping(value = "/{id}/{currency}")
	public Book findBook(@PathVariable("id") Long id, @PathVariable("currency") String currency) {
		
		Optional<Book> optionalBook = repository.findById(id);
		
		Book book = optionalBook.orElseThrow(() -> new RuntimeException("Book not found"));
		
		HashMap<String, String> params = new HashMap<>();
		params.put("amount", book.getPrice().toString());
		params.put("from", "USD");
		params.put("to", currency);
		
		var response = new RestTemplate()
			.getForEntity("http://localhost:8000/cambio-service/{amount}/{from}/{to}",
					Cambio.class,
					params);
		
		var cambio = response.getBody();
		
		var port = enviroment.getProperty("local.server.port");
		
		book.setEnvironment(port);
		book.setPrice(cambio.getConvertedValue());
		return book;
	}

}
