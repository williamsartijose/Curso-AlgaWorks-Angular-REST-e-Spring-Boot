package com.williamsarti.ProjetoSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.williamsarti.ProjetoSpring.model.Lancamento;
import com.williamsarti.ProjetoSpring.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository
    extends
        JpaRepository<Lancamento,Long>,
        LancamentoRepositoryQuery
{

}