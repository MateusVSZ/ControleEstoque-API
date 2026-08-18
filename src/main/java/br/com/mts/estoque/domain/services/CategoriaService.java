/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mts.estoque.domain.services;

import br.com.mts.estoque.repositories.CategoriaRepository;
import br.com.mts.estoque.repositories.ProdutoRepository;
import br.com.mts.estoque.domain.models.Categoria;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author digma
 */
@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

   
}
