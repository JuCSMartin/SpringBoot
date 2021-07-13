package br.com.generation.blogpessoal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.generation.blogpessoal.model.Usuario;
import br.com.generation.blogpessoal.repository.UsuarioRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioControllerTest {

	@Autowired
    private TestRestTemplate testRestTemplate;

    private Usuario usuario;
    private Usuario usuarioUpdate;
    private Usuario usuarioAdmin;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeAll
    public void start() {

    	usuarioAdmin = new Usuario(null, "Administrador", "admin@email.com.br", "admin123");
    	
    		if(!usuarioRepository.findByUsuario(usuarioAdmin.getUsuario()).isPresent()) {
    			
    			HttpEntity <Usuario> request = new HttpEntity <Usuario> (usuarioAdmin);
    			testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST, request, Usuario.class);
    		}
    	
    	usuario = new Usuario(0L, "João da Silva", "joao@email.com.br", "13456");

    	usuarioUpdate = new Usuario(1L, "João da Silva Souza", "joao@email.com.br", "joao123");
  
    }

    @Test
    @Order(1)
    @DisplayName("✔ Cadastrar Usuário!")
    public void deveRealizarPostUsuario() {

        HttpEntity <Usuario> request = new HttpEntity <Usuario> (usuario);
        ResponseEntity <Usuario> resposta = testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST, request, Usuario.class);
        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
    
    }

    @Test
    @Order(2)
    @DisplayName("👍 Listar todos os Usuários!")
    public void deveMostrarTodosUsuarios() {
       
    	ResponseEntity <String> resposta = testRestTemplate.withBasicAuth("admin@email.com.br", "admin123").exchange("/usuarios/todos", HttpMethod.GET, null, String.class);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());

  }

    @Test
    @Order(3)
    @DisplayName("😳 Alterar Usuário!")
    public void deveRealizarPutUsuario() {
        
        HttpEntity <Usuario> request = new HttpEntity <Usuario> (usuarioUpdate);
        ResponseEntity <Usuario> resposta = testRestTemplate.withBasicAuth("admin@email.com.br", "admin123").exchange("/usuarios/alterar", HttpMethod.PUT, request, Usuario.class);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());

  }

}
