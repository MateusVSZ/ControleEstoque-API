package br.com.mts.estoque.api.controllers;

import br.com.mts.estoque.domain.dtos.RelatorioEstoqueDTO;
import br.com.mts.estoque.domain.dtos.RelatorioEstoqueSaidasDTO;
import br.com.mts.estoque.repositories.ProdutoRepository;
import br.com.mts.estoque.domain.models.Produto;
import br.com.mts.estoque.domain.services.ProdutoService;
import br.com.mts.estoque.repositories.ProdutoPercentualProJection;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author digma
 */
@RestController

public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoRepository produtoRepository;
    
    

    @GetMapping("/produto")
    public List<Produto> findAll() {
        List<Produto> result = produtoRepository.findAll();
        return result;

    }

    /**
     * result é uma variavel do tipo Optional<>. Quando o método for chamado ele
     * vai verificar se existe ou não result.get() na linha 45. pq result é so a
     * caixa, como é uma condição que sempre vai retornar true ele vai retornar
     * o valor, dá erro pq se for só result ele não sabe se tem ou não é só a
     * caixa.
     *
     * @param id
     * @return
     */
    @GetMapping("/produto/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id) {
        Optional<Produto> result = produtoRepository.findById(id); //Optional(pode ou nao ter). O Jpa pede ele quando for id
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/produto/nome/{nome}")
    public ResponseEntity<List<Produto>> findByNameContainingIgnoreCase(@PathVariable String nome) {
        List<Produto> result = produtoRepository.findByNomeContainingIgnoreCase(nome);
        if (!result.isEmpty()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    /**
     * devolve um relatorio de movimentações com base em dataInicio e dataFim
     *
     * informar a dataInicio e dataFim no formato AAAA-MM-DD na request
     * QueryParams.
     *
     *
     * @param dataInicio
     * @param dataFim
     * @return
     */
    @GetMapping("/produto/relatorios")
    public ResponseEntity<List<RelatorioEstoqueDTO>> relatorio(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate dataInicio,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate dataFim) {

        LocalDateTime inicio = (dataInicio != null) ? dataInicio.atStartOfDay() : null;
        LocalDateTime fim = (dataFim != null) ? dataFim.atTime(23, 59, 59) : null; // definir a a ultima data para H:23:59:59 

        List<RelatorioEstoqueDTO> relatorio = produtoService.gerarRelatorio(inicio, fim);

        return ResponseEntity.ok(relatorio);
    }

    @GetMapping("/produto/relatorio/saidas")
    public ResponseEntity<List<RelatorioEstoqueSaidasDTO>> relatorioSaida(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate dataInicio,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate dataFim) {

        LocalDateTime inicio = (dataInicio != null) ? dataInicio.atStartOfDay() : null;
        LocalDateTime fim = (dataFim != null) ? dataFim.atTime(23, 59, 59) : null;

        List<RelatorioEstoqueSaidasDTO> relatorioSaidas = produtoService.gerarRelatorioMaiorSaida(inicio, fim);

        return ResponseEntity.ok(relatorioSaidas);
    }

    @GetMapping("/produto/relatorio/percentual")
    public ResponseEntity<List<ProdutoPercentualProJection>> relatorioPercentual() {
        List<ProdutoPercentualProJection> result = produtoRepository.findProdutosQuantidadeMinMax();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/produto")
    @ResponseStatus(HttpStatus.CREATED)
    public Produto salvar(@RequestBody Produto produto) {

        return produtoRepository.save(produto);

    }

    @PutMapping("/produto/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody @Valid Produto produto) {

        if (produtoRepository.existsById(id)) {
            produto.setId(id);
            produto = produtoRepository.save(produto);
            return ResponseEntity.ok(produto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/produto/{id}")
    public ResponseEntity<Produto> deletar(@PathVariable Long id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
