package br.org.generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.org.generation.blogPessoal.model.Usuario;
import br.org.generation.blogPessoal.model.UsuarioLogin;
import br.org.generation.blogPessoal.repository.UsuarioRepository;
import br.org.generation.blogPessoal.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
	
		// Injetando a interface repositório
		@Autowired
		private UsuarioRepository usuarioRepository;
	
		// Injetando a classe de serviço
		@Autowired
		private UsuarioService usuarioService;
		
		// Consultar usuários
		@GetMapping("/all")
		public ResponseEntity<List<Usuario>> getAll() {
			return ResponseEntity.ok(usuarioRepository.findAll());
		}
		
		// Consultar usuários por id
		@GetMapping("/{id}")
		public ResponseEntity<Usuario> getById(@PathVariable long id){
			return usuarioRepository.findById(id).map(resp -> ResponseEntity.ok(resp))
					.orElse(ResponseEntity.notFound().build());				
		}
		
		@PostMapping("/logar")
		public ResponseEntity<UsuarioLogin> loginUsuario(@RequestBody Optional <UsuarioLogin> usuarioLogin){
			
			return usuarioService.logarUsuario(usuarioLogin)
				// Se der tudo certo vai mapear a resposta e exibir os atributos de usuarioLogin
				.map(resp -> ResponseEntity.ok(resp))
				// Se der errado não irá autorizar
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
		}

		@PostMapping("/cadastrar")
		public ResponseEntity<Usuario> postUsuario(@RequestBody Usuario usuario){
			
			return usuarioService.cadastrarUsuario(usuario)
				.map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
				// Se der errado irá retornar o erro 400
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
		}
		
		@PutMapping("/alterar")
		public ResponseEntity<Usuario> putUsuario(@RequestBody Usuario usuario){
			
			return usuarioService.atualizarUsuario(usuario)
				// Se der tudo certo vai retornar um ok
				.map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
		
		@DeleteMapping("/{id}")
		public void deleteUsuario(@PathVariable long id) {

			Optional<Usuario> usuario = usuarioRepository.findById(id);
			
			if (usuario.isPresent()) {
				usuarioRepository.deleteById(id);
			}else{
				throw new ResponseStatusException(
			          	HttpStatus.NOT_FOUND, "Usuário não encontrado!", null);
			}
	}
	
}