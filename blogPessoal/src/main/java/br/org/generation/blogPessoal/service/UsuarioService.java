package br.org.generation.blogPessoal.service;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.org.generation.blogPessoal.model.UserLogin;
import br.org.generation.blogPessoal.model.Usuario;
import br.org.generation.blogPessoal.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository userRepository;
	
	public Optional<Usuario> CadastrarUsuario(Usuario usuario) {
		
		if(userRepository.findByUsuario(usuario.getUsuario()).isPresent())
			throw new ResponseStatusException(
				HttpStatus.BAD_REQUEST, "Usuário já existe!", null);
		
		int idade = Period.between(usuario.getDataNascimento(), LocalDate.now()).getYears();
		
		if(idade < 18)
			throw new ResponseStatusException(
						HttpStatus.BAD_REQUEST, "Usuário menor de 18 anos", null);
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);
		
		return Optional.of(userRepository.save(usuario));
	}
	
		public Optional<Usuario> atualizarUsuario(Usuario usuarioLogin){
		
		if(userRepository.findById(usuarioLogin.getId()).isPresent()) {
			int idade = Period.between(usuarioLogin.getDataNascimento(), LocalDate.now()).getYears();
			
			if(idade < 18)
				throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST, "Usuário menor de 18 anos", null);
					
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			String senhaEncoder = encoder.encode(usuarioLogin.getSenha());
			usuarioLogin.setSenha(senhaEncoder);
			
			return Optional.of(userRepository.save(usuarioLogin));
		
		} else {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Usuário não encontrado!", null);	
		}
		
	}
	
	
	public Optional<UserLogin> Logar(Optional<UserLogin> userLogin) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = userRepository.findByUsuario(userLogin.get().getUsuario());
		
		if(usuario.isPresent()) {
			if(encoder.matches(userLogin.get().getSenha(), usuario.get().getSenha())) {
			
			String auth = userLogin.get().getUsuario() + ":" + userLogin.get().getSenha();
			byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
			String authHeader = "Basic " + new String(encodeAuth);
			
			userLogin.get().setId(usuario.get().getId());
			userLogin.get().setNome(usuario.get().getNome());
			userLogin.get().setSenha(usuario.get().getSenha());
			userLogin.get().setToken(authHeader);
			
			return userLogin;
			
		}
	
	}

		throw new ResponseStatusException(
				HttpStatus.UNAUTHORIZED, "Usuário ou senha inválidos!", null);
		
	}
	

}
