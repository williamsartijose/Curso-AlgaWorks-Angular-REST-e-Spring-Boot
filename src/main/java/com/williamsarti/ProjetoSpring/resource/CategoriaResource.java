package com.williamsarti.ProjetoSpring.resource;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.williamsarti.ProjetoSpring.model.Categoria;
import com.williamsarti.ProjetoSpring.repository.CategoriaRepository;



@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaRepository CategoriaRepository;

    @GetMapping
    public List<Categoria> listar() {
        return CategoriaRepository.findAll();
    }
    
}