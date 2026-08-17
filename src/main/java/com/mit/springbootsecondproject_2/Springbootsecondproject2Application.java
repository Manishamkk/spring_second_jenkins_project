package com.mit.springbootsecondproject_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Springbootsecondproject2Application {

	public static void main(String[] args) {
		SpringApplication.run(Springbootsecondproject2Application.class, args);
	}

	@GetMapping("/")
	public String display() {
		return "Hello India";
	}
	
}
