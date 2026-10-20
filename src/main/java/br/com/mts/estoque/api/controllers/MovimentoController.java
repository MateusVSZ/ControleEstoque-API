/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mts.estoque.api.controllers;

import br.com.mts.estoque.domain.dtos.MovimentoDTO;
import br.com.mts.estoque.domain.models.Movimento;
import br.com.mts.estoque.domain.services.MovimentoService;
import br.com.mts.estoque.repositories.MovimentoRepository;
import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author digma
 */
@RestController
@RequestMapping("/movimento")
public class MovimentoController {

    @Autowired
    private MovimentoRepository movimentoRepository;

    @Autowired
    private MovimentoService movimentoService;

    @GetMapping
    public List<Movimento> findAll() {
        List<Movimento> result = movimentoRepository.findAll();
        return result;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Movimento> findById(@PathVariable Long id) {
        Optional<Movimento> result = movimentoRepository.findById(id);

        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/id/saidas")
    public ResponseEntity<List<Movimento>> SaidasProduto(Movimento movimento) {
        List<Movimento> saidas = movimentoRepository.findByQtdLessThanOrderByDataMovimentoDesc(BigDecimal.ZERO);
        if (!saidas.isEmpty()) {
            return ResponseEntity.ok(saidas);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/entradas")
    public ResponseEntity<List<Movimento>> EntradasProduto(Movimento movimento) {
        List<Movimento> entradas = movimentoRepository.findByQtdGreaterThanOrderByDataMovimentoDesc(BigDecimal.ZERO);
        if (!entradas.isEmpty()) {
            return ResponseEntity.ok(entradas);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Movimento cadastrar(@RequestBody Movimento movimento) {
        Movimento result = movimentoService.saldo(movimento);
        return result;
    }

    @PostMapping("/atualiza")
    public ResponseEntity<Movimento> atualizaEstoque(@RequestBody @Valid MovimentoDTO movtoDTO) {

        Movimento response = movimentoService.atualizaEstoque(movtoDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity Movimento(@PathVariable Long id) {

        if (movimentoRepository.existsById(id)) {

            movimentoRepository.deleteById(id);
            return ResponseEntity.noContent().build();

        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
