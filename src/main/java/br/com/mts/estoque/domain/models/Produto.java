package br.com.mts.estoque.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

/**
 *
 * @author digma
 */
@Entity
@Table(name="produto")
public class Produto {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
   
    @Size(max = 120)
    @NotBlank
    private String nome;
   
    @NotNull
    @Column(precision = 10, scale = 2)
    private BigDecimal valor_unitario;
    
    @NotNull
    @Column(name = "quantidade",precision = 10, scale = 2)
    private BigDecimal saldo;
    
    public Produto() {
    }

    public Produto(Long id, Categoria categoria, String nome, BigDecimal valor_unitario, BigDecimal saldo) {
        this.id = id;
        this.categoria = categoria;
        this.nome = nome;
        this.valor_unitario = valor_unitario;
        this.saldo = saldo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor_unitario() {
        return valor_unitario;
    }

    public void setValor_unitario(BigDecimal valor_unitario) {
        this.valor_unitario = valor_unitario;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

}