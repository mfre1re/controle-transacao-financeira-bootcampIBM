package br.com.desafio02bootcamp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Transação Financeira Api", version = "1", description = "Aplicação desenvolvida para o bootcamp da IBM"))
@EnableScheduling
public class Desafio02Application {

	public static void main(String[] args) {

		SpringApplication.run(Desafio02Application.class, args);
	}

}
