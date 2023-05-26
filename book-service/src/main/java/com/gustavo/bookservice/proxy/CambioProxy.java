package com.gustavo.bookservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.gustavo.bookservice.response.Cambio;

// O balanceamento de carga é feito pelo lado do cliente do Spring Cloud Eureka. (book faz o balanceamento de cambio)
// Book vai até o Eureka e obtém a lista de endereços Cambio disponíveis, baseado nessa lista o Spring Cloud Load balancer 
// (que vem no eureka client) dentro do microsserviço client (Book) distribui a carga.
@FeignClient(name = "cambio-service")
public interface CambioProxy {
	
	@GetMapping(value = "/cambio-service/{amount}/{from}/{to}")
	public Cambio getCambio(
			@PathVariable("amount") Double amount,
			@PathVariable("from") String from,
			@PathVariable("to") String to);

}
