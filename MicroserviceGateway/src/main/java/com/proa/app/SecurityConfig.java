package com.proa.app;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity        //permite manejar dos objetos (mono= 1 proceso)  y (flux = multiples procesos)
public class SecurityConfig {
    
	@Bean
	public SecurityWebFilterChain securityFilter(ServerHttpSecurity httpRequest) {
		
		httpRequest
		.authorizeExchange(exchange-> exchange.anyExchange().authenticated())
		.oauth2ResourceServer(oauth2->oauth2.jwt(Customizer.withDefaults()));
		
		return httpRequest.build();
	}
	 
}
