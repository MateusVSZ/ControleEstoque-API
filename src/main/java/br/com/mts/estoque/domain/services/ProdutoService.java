/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mts.estoque.domain.services;

import br.com.mts.estoque.domain.dtos.RelatorioEstoqueDTO;
import br.com.mts.estoque.domain.dtos.RelatorioEstoqueSaidasDTO;
import br.com.mts.estoque.repositories.MovimentoRepository;
import br.com.mts.estoque.repositories.ProdutoRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author digma
 */
@Service
public class ProdutoService {

    @Autowired
    private MovimentoRepository movimentoRepository;
    
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<RelatorioEstoqueDTO> gerarRelatorio(LocalDateTime dataInicio, LocalDateTime dataFim) {

        List<Object[]> result = movimentoRepository.findRelatorioEstoqueNative(dataInicio, dataFim);
        List<RelatorioEstoqueDTO> relatorio = new ArrayList<>();

        for (Object[] linha : result) {
            RelatorioEstoqueDTO dto = new RelatorioEstoqueDTO();
            dto.setProdutoId(((Number) linha[0]).longValue());
            dto.setNomeProduto((String) linha[1]);
            dto.setValor_unitario((BigDecimal) linha[2]);
            dto.setTotalEntradas(((Number) linha[3]).doubleValue());
            dto.setTotalSaidas(((Number) linha[4]).doubleValue());
            relatorio.add(dto);
        }

        return relatorio;

    }

    public List<RelatorioEstoqueSaidasDTO> gerarRelatorioMaiorSaida(LocalDateTime dataIncio, LocalDateTime dataFim) {
        List<Object[]> result = produtoRepository.findSaidasMaiorVolume(dataIncio, dataFim);
        List<RelatorioEstoqueSaidasDTO> relatorioSaidas =  new ArrayList<>();
        
        for(Object[] linha : result){
           RelatorioEstoqueSaidasDTO dto = new RelatorioEstoqueSaidasDTO();
           dto.setProdutoId(((Number) linha[0]).longValue());
           dto.setNome((String) linha[1]);
           dto.setTotalSaidas(((Number) linha[2]).doubleValue());
           dto.setQtd((BigDecimal) linha[3]);
           relatorioSaidas.add(dto);
        
        }
        return relatorioSaidas;
    }
}
