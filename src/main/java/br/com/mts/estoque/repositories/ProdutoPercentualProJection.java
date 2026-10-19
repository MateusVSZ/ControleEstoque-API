/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.mts.estoque.repositories;

import java.math.BigDecimal;

/**
 *
 * @author digma
 */
public interface ProdutoPercentualProJection {

    BigDecimal getTotalPercentual();

    Long getId();

    String getNome();
    
    String getCategoria();

    String getValorMinMax();
}
