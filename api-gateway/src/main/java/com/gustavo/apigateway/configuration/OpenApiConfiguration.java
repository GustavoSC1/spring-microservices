package com.gustavo.apigateway.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class OpenApiConfiguration {
	
	// https://piotrminkowski.com/2020/02/20/microservices-api-documentation-with-springdoc-openapi/
	@Bean
	// Tem que ser carregado assim que API Gateway estiver inicializando
	@Lazy(false)
	public List<GroupedOpenApi> apis(
			SwaggerUiConfigParameters config, 
			RouteDefinitionLocator locator) {
		
		// Obtem todas as rotas definidas para serviços. 
		// As páginas de documentação viram objetos definition
		// .block() porque enquanto estiver lendo é necessário bloquear o arquivo
		var definitions = locator.getRouteDefinitions().collectList().block();
		
		definitions.stream().filter(
					routeDefinition -> routeDefinition.getId()
						.matches(".*-service")) // Obtem as rotas que que possuem service no nome (Ex: book-service e cambio-service)
							.forEach(routeDefinition -> {
								String name = routeDefinition.getId();
								config.addGroup(name);
								// Permite dividir as definições de OpenAPI em diferentes grupos com um determinado nome.
								GroupedOpenApi.builder() // Constrói o conjunto de documentações Swagger
									.pathsToMatch("/" + name + "/**")
									.group(name).build();
							});
		
		return new ArrayList<>();
	}

}
