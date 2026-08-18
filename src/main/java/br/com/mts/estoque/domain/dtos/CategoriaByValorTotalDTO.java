/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mts.estoque.domain.dtos;

import java.math.BigDecimal;




/**
 *
 * @author digma
 */

public class CategoriaByValorTotalDTO {
   
    private BigDecimal valorTotal;
    
    private String categoria;

    public CategoriaByValorTotalDTO() {
    }

    public CategoriaByValorTotalDTO(BigDecimal valorTotal, String categoria) {
        this.valorTotal = valorTotal;
        this.categoria = categoria;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    
} 
   
   
