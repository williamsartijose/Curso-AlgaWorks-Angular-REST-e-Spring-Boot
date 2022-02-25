package com.williamsarti.ProjetoSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.williamsarti.ProjetoSpring.model.Lancamento;

public interface LancamentoRepository
    extends
        JpaRepository<Lancamento,Long>
{

}