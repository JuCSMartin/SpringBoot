package br.com.generation.lojadegames.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.generation.lojadegames.model.Produto;

public interface ProdutoRepository extends JpaRepository <Produto, Long> {

	public List<Produto> findAllByTituloContainingIgnoreCase (String titulo);
}
