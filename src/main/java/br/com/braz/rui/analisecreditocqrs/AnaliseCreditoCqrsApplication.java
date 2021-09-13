package br.com.braz.rui.analisecreditocqrs;

import br.com.braz.rui.analisecreditocqrs.application.config.database.DatabaseConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AnaliseCreditoCqrsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnaliseCreditoCqrsApplication.class, args);
	}

}
