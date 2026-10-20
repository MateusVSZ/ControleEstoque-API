/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mts.estoque.api.controllers;

import br.com.mts.estoque.repositories.CategoriaByValorTotalProjection;
import br.com.mts.estoque.repositories.CategoriaRepository;
import br.com.mts.estoque.domain.models.Categoria;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author digma
 */
@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public List<Categoria> findAll() {
        List<Categoria> result = categoriaRepository.findAll();
        return result;
    }

    @GetMapping("/resumo")
    public List<CategoriaByValorTotalProjection> ValorTotalByCategoria (){
    List<CategoriaByValorTotalProjection> result = categoriaRepository.ValorTotalByCategoria();
        return result;
        
    }

    @GetMapping("/id/{Id}")
    public ResponseEntity<Categoria> findById(@PathVariable Long Id) {
        Optional<Categoria> result = categoriaRepository.findById(Id);

        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.notFound().build();

        }
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Categoria>> findByCategoria(@PathVariable @Valid String nome ) {
        List<Categoria> result = categoriaRepository.findByCategoriaContainingIgnoreCase(nome);
        if (!result.isEmpty()) {

            return ResponseEntity.ok(result);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria Adicionar(@RequestBody Categoria categoria) {
        return categoriaRepository.save(categoria);

    }

    @PutMapping("/id/{Id}")
    public ResponseEntity<Categoria> Atualizar(@PathVariable Long Id, @RequestBody Categoria categoria) {

        if (categoriaRepository.existsById(Id)) {
            categoria.setId(Id);
            categoria = categoriaRepository.save(categoria);
            return ResponseEntity.ok(categoria);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/id/{Id}")
    public ResponseEntity<Categoria> Deletar(@PathVariable Long Id) {
        if (categoriaRepository.existsById(Id)) {
            categoriaRepository.deleteById(Id);
            return ResponseEntity.noContent().build();

        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
