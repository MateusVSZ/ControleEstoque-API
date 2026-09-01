package br.com.mts.estoque.domain.dtos;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 *
 * @author digma
 */
public class MovimentoDTO {
    
    @NotNull
    Long idProduto;
    
    @NotNull
    BigDecimal qtd;

    public MovimentoDTO() {
    }

    public MovimentoDTO(Long idProduto, BigDecimal qtd) {
        this.idProduto = idProduto;
        this.qtd = qtd;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public BigDecimal getQtd() {
        return qtd;
    }

    public void setQtd(BigDecimal qtd) {
        this.qtd = qtd;
    }
    
    
}
