package br.com.mts.estoque.repositories;

import br.com.mts.estoque.domain.dtos.MovimentoDTO;
import br.com.mts.estoque.domain.models.Movimento;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author digma
 */
public interface MovimentoRepository extends JpaRepository<Movimento, Long> {
    
    List<Movimento> findByQtdLessThanOrderByDataMovimentoDesc(BigDecimal qtd);
    List<Movimento> findByQtdGreaterThanOrderByDataMovimentoDesc(BigDecimal qtd);

    
}
