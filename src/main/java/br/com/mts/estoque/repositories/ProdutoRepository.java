package br.com.mts.estoque.repositories;

import br.com.mts.estoque.domain.models.Produto;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author digma
 */
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByNomeContainingIgnoreCase(String produto);

    @Query(value
            = "SELECT p.id, p.nome, p.valor_unitario, "
            + "SUM(CASE WHEN m.qtd < 0 THEN -m.qtd ELSE 0 END) AS saidas "
            + "FROM movimento m "
            + "JOIN produto p ON p.id = m.id_produto "
            + "WHERE (:dataInicio IS NULL OR m.data_movimento >= :dataInicio) "
            + "AND (:dataFim IS NULL OR m.data_movimento <= :dataFim) "
            + "GROUP BY p.id "
            + "ORDER BY saidas DESC",
            nativeQuery = true
    )
    List<Object[]> findSaidasMaiorVolume(@Param("dataInicio") LocalDateTime dataInicio,
            @Param("dataFim") LocalDateTime dataFim);

    //arrumar query
    @Query(value = """
                    SELECT
                        p.id,
                        p.nome,
                        p.valor_unitario,
                        p.quantidade,
                        p.id_categoria,
                        c.categoria,
                        CASE
                            WHEN p.quantidade >= 0 and p.quantidade <50 THEN 'Estoque mínimo' 
                            WHEN p.quantidade =100 THEN 'Estoque máximo'
                        ELSE 'Estoque na media'
                        END AS valorMinMax,
                       ROUND(p.quantidade * 1.0 / 100 , 2) AS totalPercentual
                   FROM categoria AS c
                   INNER JOIN produto AS p ON p.id_categoria = c.id
                    WHERE p.quantidade >= 0
                      AND p.quantidade <= 100
                      
                      ORDER BY totalPercentual DESC;
                   
                  """, nativeQuery = true)
    List<ProdutoPercentualProJection> findProdutosQuantidadeMinMax();

}
