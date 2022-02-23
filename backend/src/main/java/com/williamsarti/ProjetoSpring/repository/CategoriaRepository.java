package com.williamsarti.ProjetoSpring.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.williamsarti.ProjetoSpring.model.Categoria;


@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    

}
