package br.org.generation.blogPessoal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Anotação para dizer ao Spring que essa classe deverá ser usada para configurar toda a aplicação.
@SpringBootApplication
public class BlogPessoalApplication {

	public static void main(String[] args) {
		
		// SpringApplication.run -> inicialização da aplicação
		SpringApplication.run(BlogPessoalApplication.class, args);
	}

}
