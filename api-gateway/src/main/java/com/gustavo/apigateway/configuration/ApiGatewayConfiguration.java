package com.gustavo.apigateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class ApiGatewayConfiguration {
	
	// O método gatewayRouter recebe um RouteLocatorBuilder que pode ser usado para criar rotas. Além de criar rotas, 
	// o RouteLocatorBuilder permite adicionar predicados e filtros às suas rotas para que você possa manipular a rota 
	// com base em determinadas condições, bem como alterar a solicitação/resposta conforme desejar.

	// Foi criadar uma rota que roteia uma solicitação para https://httpbin.org/get quando uma solicitação é 
	// feita para o Gateway em /get. Em nossa configuração dessa rota, adicionamos um filtro que adiciona 
	// o cabeçalho da solicitação e um parâmetro Hello com o valor World à solicitação antes de ser roteada.
	
	// Obs: Comentado pq foi transferido para o application.yml
	/*
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		return builder.routes().route(p -> p.path("/get")
				.filters(f -> f.addRequestHeader("Hello", "World")
						.addRequestParameter("Hello", "World"))
					.uri("http://httpbin.org:80"))
				// A aplicação server não faz load balancer, mas os clients incluindo o API Gateway fazem.
				// Vai até o Eureka e obtém os endereços disponíveis para acessar cada um dos serviços e dentre os quais ele 
				// poderá distribuir a carga. 
				.route(p -> p.path("/cambio-service/**")
						.uri("lb://cambio-service")) 
				.route(p -> p.path("/book-service/**")
						.uri("lb://book-service"))
				.build();
	}
	*/
}
