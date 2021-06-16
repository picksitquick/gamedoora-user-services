package com.gamedoora.backend.userservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = LiquibaseAutoConfiguration.class)
public class GamedooraUserServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamedooraUserServicesApplication.class, args);
	}

}
