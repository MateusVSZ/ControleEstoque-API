/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mts.estoque.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

/**
 *
 * @author digma
 */
@Entity
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_produto;
    
    @ManyToOne
    private Categoria categoria;
    
    
    @Valid
    @Size(max = 120)
    @NotNull
    private String nome;
    
    
    @Valid
    @NotNull
    @Column(name = "valor_unitario")
    private BigDecimal valor;

    private int quantidade;
    
    public Produto() {
    }

    public Produto(Long id_produto, Categoria categoria, String nome, BigDecimal valor, int quantidade) {
        this.id_produto = id_produto;
        this.categoria = categoria;
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public Long getId_produto() {
        return id_produto;
    }

    public void setId_produto(Long id_produto) {
        this.id_produto = id_produto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }
    public int quantidade(){
        return quantidade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setQuantidade(int quantidade){
        this.quantidade = quantidade;
       
    }

    public BigDecimal getValor_unitario() {
        return valor;
    }

    public void setValor_unitario(BigDecimal valor) {
        this.valor = valor;
    }
    
    
  
    
    
    
}
