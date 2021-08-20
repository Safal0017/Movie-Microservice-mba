package com.upgrad.movieapp.moviemicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovieMicroserviceApplication {

	public static void main(String[] args) {

		SpringApplication.run(MovieMicroserviceApplication.class, args);
		System.out.println("Movie Microservice Application Started and running up on the server!!");
	}

}
