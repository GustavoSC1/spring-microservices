package com.gustavo.bookservice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gustavo.bookservice.model.Book;
import com.gustavo.bookservice.repository.BookRepository;

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
		
		var port = enviroment.getProperty("local.server.port");
		
		book.setEnvironment(port);
		
		return book;
	}

}
