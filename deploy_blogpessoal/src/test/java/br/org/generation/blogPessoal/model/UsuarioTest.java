package br.org.generation.blogPessoal.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UsuarioTest {
	
	 	public Usuario usuario;
		public Usuario usuarioNulo = new Usuario();

		// Verificar se alguma condição das anotações não estão sendo respeitadas
		@Autowired
		private  ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		
		Validator validator = factory.getValidator();

		// Executar este método antes de cada teste
		@BeforeEach
		public void start() {

			// Criando data de nascimento
			LocalDate data = LocalDate.parse("2000-07-22", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			// Vai preencher os dados do usuário
	        usuario = new Usuario(0L, "João da Silva", "joao@email.com.br", "13465278", data);

		}

		@Test
		@DisplayName("✔ Valida Atributos Não Nulos")
		void testValidaAtributos() {
			
			// Set -> não permite duplicação de dados (o list permite)
			// ConstraintViolation -> vai guardar as divergências em Set
			Set<ConstraintViolation<Usuario>> violacao = validator.validate(usuario);
			
			System.out.println(violacao.toString());

			// Se a lista estiver vazia é porque os testes estão de acordo
			assertTrue(violacao.isEmpty());
		}

		// Segundo teste passando usuário nulo
		@Test
		@DisplayName("✖ Não Valida Atributos Nulos")
		void testNaoValidaAtributos() {

			Set<ConstraintViolation<Usuario>> violacao = validator.validate(usuarioNulo);
			
			System.out.println(violacao.toString());

			// A lista terá erros, pois foram passados dados nulos
			assertTrue(violacao.isEmpty());
		}

}
