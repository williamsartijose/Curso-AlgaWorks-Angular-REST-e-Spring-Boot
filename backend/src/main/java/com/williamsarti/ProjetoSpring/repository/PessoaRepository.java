package com.williamsarti.ProjetoSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.williamsarti.ProjetoSpring.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}