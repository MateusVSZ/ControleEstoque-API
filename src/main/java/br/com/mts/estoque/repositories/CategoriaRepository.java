/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.mts.estoque.repositories;

import br.com.mts.estoque.domain.models.Categoria;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author digma
 */
public interface CategoriaRepository  extends JpaRepository<Categoria, Long>{
    List<Categoria> findByCategoriaContainingIgnoreCase(String categoria);
    
    @Query(nativeQuery = true, value = """
                                       
        SELECT categoria, SUM( p.quantidade * p.valor_unitario) AS valorTotal
        FROM categoria AS c
        INNER JOIN produto AS p ON p.categoria_id = c.id
        GROUP BY c.id
                                    
                                                                    """)
    List<CategoriaByValorTotalProjection> ValorTotalByCategoria ();
        
   
}
