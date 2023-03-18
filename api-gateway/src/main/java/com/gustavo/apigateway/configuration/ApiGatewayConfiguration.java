package com.gustavo.apigateway.configuration;

import java.util.function.Function;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
	
	// O método gatewayRouter recebe um RouteLocatorBuilder que pode ser usado para criar rotas. Além de criar rotas, 
	// o RouteLocatorBuilder permite adicionar predicados e filtros às suas rotas para que você possa manipular a rota 
	// com base em determinadas condições, bem como alterar a solicitação/resposta conforme desejar.

	// Foi criadar uma rota que roteia uma solicitação para https://httpbin.org/get quando uma solicitação é 
	// feita para o Gateway em /get. Em nossa configuração dessa rota, adicionamos um filtro que adiciona 
	// o cabeçalho da solicitação e um parâmetro Hello com o valor World à solicitação antes de ser roteada.
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		Function<PredicateSpec, Buildable<Route>> function = 
				p -> p.path("/get")
						.filters(f -> f.addRequestHeader("Hello", "World")
								.addRequestParameter("Hello", "World"))
						.uri("http://httpbin.org:80");
		return builder.routes().route(function).build();
	}

}
