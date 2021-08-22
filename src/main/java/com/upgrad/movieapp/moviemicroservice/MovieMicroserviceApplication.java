package com.upgrad.movieapp.moviemicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@SpringBootApplication
public class MovieMicroserviceApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(MovieMicroserviceApplication.class, args);
		System.out.println("Movie Microservice Application Started and running up on the server!!");
	}

	@Bean
	public RestTemplate getRestTemplate() { return new RestTemplate(); }

}
