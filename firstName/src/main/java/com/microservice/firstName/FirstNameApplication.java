package com.microservice.firstName;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class FirstNameApplication {
	
	@Bean
	WebClient.Builder getWebClient() {
		
		return WebClient.builder();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(FirstNameApplication.class, args);
	}
}
