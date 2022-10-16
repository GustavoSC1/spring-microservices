package com.gustavo.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustavo.bookservice.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
