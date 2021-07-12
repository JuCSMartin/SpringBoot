package br.com.generation.lojadegames.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.generation.lojadegames.model.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {

	public Optional<Usuario> findByEmail (String email);
}
