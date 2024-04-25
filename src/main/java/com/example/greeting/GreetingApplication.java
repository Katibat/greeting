package com.example.greeting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GreetingApplication { // http://localhost:8080/greeting

	public static void main(String[] args) {
		SpringApplication.run(GreetingApplication.class, args);
	}

}
