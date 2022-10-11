package com.gustavo.greetingservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
// É usado para carregar as novas propriedades de configuração do servidor Config sem precisar reiniciar o aplicativo e sem qualquer 
// tempo de inatividade. (/actuator/refresh)
@RefreshScope
// Permite mapear facilmente todos os arquivos de Properties e Yaml em um objeto. 
@ConfigurationProperties("greeting-service")
public class GreetingConfiguration {
	
	private String greeting;
	private String defaultValue;
		
	public GreetingConfiguration() {
		
	}

	public String getGreeting() {
		return greeting;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

}
