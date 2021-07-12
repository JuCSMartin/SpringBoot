package br.com.generation.lojadegames.service;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.generation.lojadegames.model.Usuario;
import br.com.generation.lojadegames.model.UsuarioLogin;
import br.com.generation.lojadegames.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired 
	private UsuarioRepository repository;
	
	public Optional<Usuario> cadastrarUsuario(Usuario usuario) {

		if (repository.findByEmail(usuario.getEmail()).isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email já cadastrado!", null);

		int idade = Period.between(usuario.getDataNascimento(), LocalDate.now()).getYears();

		if (idade < 18)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário menor de 18 anos.", null);

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String senhaEncoder = encoder.encode(usuario.getSenha());

		usuario.setSenha(senhaEncoder);

		return Optional.of(repository.save(usuario));
	}
	
	public Optional<Usuario> alterarUsuario(Usuario usuario){
		
		if (repository.findByEmail(usuario.getEmail()).isPresent()) {

		int idade = Period.between(usuario.getDataNascimento(), LocalDate.now()).getYears();

		if (idade < 18)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não pode ter menos de 18 anos.", null);

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String senhaEncoder = encoder.encode(usuario.getSenha());

		usuario.setSenha(senhaEncoder);

		return Optional.of(repository.save(usuario));
		
		} else {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Usuário não encontrado!", null);
		}
		
	}
	
	public Optional<UsuarioLogin> logarUsuario (Optional<UsuarioLogin> login) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		Optional<Usuario> usuario = repository.findByEmail(login.get().getEmail());
		
		if(usuario.isPresent()) {
			if(encoder.matches(login.get().getSenha(), usuario.get().getSenha())) {
				
				String auth = login.get().getEmail() + ":" + login.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				
				login.get().setToken(authHeader);

				login.get().setNome(usuario.get().getNome());
				
				return login;
			}
		}
		
		return null;
	}
}
