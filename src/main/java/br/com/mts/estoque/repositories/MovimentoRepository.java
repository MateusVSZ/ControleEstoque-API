package br.com.mts.estoque.repositories;

import br.com.mts.estoque.domain.dtos.MovimentoDTO;
import br.com.mts.estoque.domain.dtos.RelatorioEstoqueDTO;
import br.com.mts.estoque.domain.models.Movimento;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author digma
 */
public interface MovimentoRepository extends JpaRepository<Movimento, Long> {

    List<Movimento> findByQtdLessThanOrderByDataMovimentoDesc(BigDecimal qtd);

    List<Movimento> findByQtdGreaterThanOrderByDataMovimentoDesc(BigDecimal qtd);

    @Query(value
            = "SELECT p.id, p.nome, p.valor_unitario, "
            + "       SUM(CASE WHEN m.qtd > 0 THEN m.qtd ELSE 0 END) AS entradas, "
            + "       SUM(CASE WHEN m.qtd < 0 THEN -m.qtd ELSE 0 END) AS saidas "
            + "FROM movimento m "
            + "JOIN produto p ON p.id = m.id_produto "
            + "WHERE (:dataInicio IS NULL OR m.data_movimento >= :dataInicio) "
            + "AND (:dataFim IS NULL OR m.data_movimento <= :dataFim) "
            + "GROUP BY p.id, p.nome, p.valor_unitario "
            + "ORDER BY p.id",
            nativeQuery = true
    )
    List<Object[]> findRelatorioEstoqueNative(@Param("dataInicio") LocalDateTime dataInicio,
            @Param("dataFim") LocalDateTime dataFim);

}
