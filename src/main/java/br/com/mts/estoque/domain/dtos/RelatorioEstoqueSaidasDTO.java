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
public class RelatorioEstoqueSaidasDTO {
    
    private Long produtoId;
    private String nome;
    private Double totalSaidas;
    private BigDecimal qtd;

    public RelatorioEstoqueSaidasDTO() {
    }

    public RelatorioEstoqueSaidasDTO(Long produtoId, String nome, Double totalSaidas, BigDecimal qtd) {
        this.produtoId = produtoId;
        this.nome = nome;
        this.totalSaidas = totalSaidas;
        this.qtd = qtd;
    }
    

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getTotalSaidas() {
        return totalSaidas;
    }

    public void setTotalSaidas(Double totalSaidas) {
        this.totalSaidas = totalSaidas;
    }

    public BigDecimal getQtd() {
        return qtd;
    }

    public void setQtd(BigDecimal qtd) {
        this.qtd = qtd;
    }
}