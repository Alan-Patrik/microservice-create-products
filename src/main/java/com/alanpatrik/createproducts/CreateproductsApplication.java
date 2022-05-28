package com.alanpatrik.createproducts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CreateproductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreateproductsApplication.class, args);
	}

}
