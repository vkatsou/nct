package com.nct;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@OpenAPIDefinition(info = @Info(title = "Nutrition Calorie Tracker application", version = "1.0", description = "Application to track your nutritional value food intake"))
public class NctApplication {

	public static void main(String[] args) {
		SpringApplication.run(NctApplication.class, args);
	}

}
