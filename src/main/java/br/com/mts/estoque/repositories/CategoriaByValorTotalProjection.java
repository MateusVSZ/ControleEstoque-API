package br.com.mts.estoque.repositories;


import java.math.BigDecimal;


/**
 *
 * @author digma
 */
public interface CategoriaByValorTotalProjection{
   
    String getCategoria();
    BigDecimal getValorTotal();
    
}
