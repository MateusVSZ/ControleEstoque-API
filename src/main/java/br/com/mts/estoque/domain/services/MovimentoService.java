/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mts.estoque.domain.services;

import br.com.mts.estoque.domain.dtos.MovimentoDTO;
import br.com.mts.estoque.domain.models.Movimento;
import br.com.mts.estoque.domain.models.Produto;
import br.com.mts.estoque.repositories.MovimentoRepository;
import br.com.mts.estoque.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author digma
 */
@Service
public class MovimentoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Autowired
    private MovimentoRepository movimentoRepository;

    public Movimento saldo(Movimento movimento) {
        Optional<Produto> produto = produtoRepository.findById(movimento.getProduto().getId());

        if (produto.isPresent()) {

            produto.get().setSaldo(produto.get().getSaldo().add(movimento.getQtd()));

            produtoRepository.save(produto.get());

        }
        return movimentoRepository.save(movimento);

    }

    @Transactional
    public Movimento atualizaEstoque(MovimentoDTO movtoDTO) {
        
        Produto produtoBusca = produtoRepository.findById(movtoDTO.getIdProduto()).
                orElseThrow(() -> new RuntimeException("Codigo produto não existe!"));
                
        // Ok. Produto existe. Segue o jogo.
        Movimento newMovto = new Movimento();
        newMovto.setQtd(movtoDTO.getQtd()); //
        newMovto.setProduto(produtoBusca);
        newMovto.setDataMovimento(LocalDateTime.now());
        newMovto = movimentoRepository.save(newMovto);
        
        produtoBusca.setSaldo(movtoDTO.getQtd());
        produtoRepository.save(produtoBusca);
        
        return newMovto;
        
    }
}
