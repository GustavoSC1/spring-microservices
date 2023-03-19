package com.gustavo.apigateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
// Esses são filtros especiais que são aplicados condicionalmente a todas as rotas.
public class LoggingFilter implements GlobalFilter {
	
	private Logger logger = LoggerFactory.getLogger(LoggingFilter.class);
	
	// Assim que esse filtro for invocado, é registrado uma mensagem e continuado com a execução da cadeia de filtros.
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		logger.info("Original request path -> {}", exchange.getRequest().getPath());
		return chain.filter(exchange);
	}

}
