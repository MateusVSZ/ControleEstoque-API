/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mts.estoque.domain.services;

import br.com.mts.estoque.domain.dtos.RelatorioEstoqueDTO;
import br.com.mts.estoque.repositories.MovimentoRepository;
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
}
