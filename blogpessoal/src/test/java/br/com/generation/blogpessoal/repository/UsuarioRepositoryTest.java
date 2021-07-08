package br.com.generation.blogpessoal.repository;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import br.com.generation.blogpessoal.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start() {
		
		Usuario usuario = new Usuario(0L, "João da Silva", "joao@email.com.br", "123456");
		
		if(usuarioRepository.findByUsuario(usuario.getUsuario()) != null)
			usuarioRepository.save(usuario);
        
        usuario = new Usuario(0L, "Manuel da Silva", "manuel@email.com.br", "13465278");

        if(usuarioRepository.findByUsuario(usuario.getUsuario()) != null)
            usuarioRepository.save(usuario);

        usuario = new Usuario(0L, "Fred da Silva", "frederico@email.com.br", "13465278");

        if(usuarioRepository.findByUsuario(usuario.getUsuario()) != null)
            usuarioRepository.save(usuario);

       	usuario = new Usuario(0L, "Paulo Antunes", "paulo@email.com.br", "13465278");

        if(usuarioRepository.findByUsuario(usuario.getUsuario()) != null)
            usuarioRepository.save(usuario);
	}
	
	@Test
	@DisplayName("💾Retorna o nome")
	public void findFirstByUsuarioRetornaUsuario() throws Exception {
		Usuario usuario = usuarioRepository.findByUsuario("João da Silva");
		assertTrue(usuario.getNome().equals("João da Silva"));
	}
	
	@Test
    @DisplayName("💾 Retorna 3 usuarios")
    public void findAllByUsuarioContainingIgnoreCaseRetornaTresUsuarios() {
        List<Usuario> listaDeUsuarios = usuarioRepository.findAllByUsuarioContainingIgnoreCase("Silva");
        assertEquals(3, listaDeUsuarios.size());
  }

    @AfterAll
    public void end() {
        usuarioRepository.deleteAll();
    }
}
