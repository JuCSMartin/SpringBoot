package br.com.generation.farmacia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.generation.farmacia.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long >{

	public List<Produto> findAllByTituloContainingIgnoreCase(String titulo);
}
