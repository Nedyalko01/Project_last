package com.example.moonlighthotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class})
public class MoonlighthotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoonlighthotelApplication.class, args);
	}

}
