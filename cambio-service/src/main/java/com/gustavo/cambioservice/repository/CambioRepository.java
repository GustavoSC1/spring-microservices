package com.gustavo.cambioservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustavo.cambioservice.model.Cambio;

public interface CambioRepository extends JpaRepository<Cambio, Long> {

	Cambio findByFromAndTo(String from, String to);
	
}
