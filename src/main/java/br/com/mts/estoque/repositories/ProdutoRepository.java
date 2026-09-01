package br.com.mts.estoque.repositories;

import br.com.mts.estoque.domain.models.Produto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author digma
 */
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
     List<Produto> findByNomeContainingIgnoreCase(String produto);
    
}
