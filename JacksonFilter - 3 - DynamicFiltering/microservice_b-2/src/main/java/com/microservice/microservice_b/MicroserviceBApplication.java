package com.microservice.microservice_b;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MicroserviceBApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceBApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
