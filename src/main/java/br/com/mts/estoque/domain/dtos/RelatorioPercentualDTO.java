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
public class RelatorioPercentualDTO {
    
    Long id;
    String nome;

  
    String categoria;
    BigDecimal totalPercentual;
    String valorMinMax;
    

    public RelatorioPercentualDTO() {
    }

    public RelatorioPercentualDTO(Long id, String categoria, BigDecimal totalPercentual, String valorMinMax) {
        this.id = id;
        this.categoria = categoria;
        this.totalPercentual = totalPercentual;
        this.valorMinMax = valorMinMax;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getTotalPercentual() {
        return totalPercentual;
    }

    public void setTotalPercentual(BigDecimal totalPercentual) {
        this.totalPercentual = totalPercentual;
    }

    public String getValorMinMax() {
        return valorMinMax;
    }

    public void setValorMinEmax(String valorMinMax) {
        this.valorMinMax = valorMinMax;
    }

}