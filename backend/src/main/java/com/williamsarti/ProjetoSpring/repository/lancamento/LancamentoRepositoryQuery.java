package com.williamsarti.ProjetoSpring.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.williamsarti.ProjetoSpring.model.Lancamento;
import com.williamsarti.ProjetoSpring.repository.filter.LancamentoFilter;
import com.williamsarti.ProjetoSpring.repository.projection.ResumoLancamento;

public interface LancamentoRepositoryQuery
{

    public Page<Lancamento> filtrar(
        LancamentoFilter lancamentoFilter,
        Pageable pageable );

    public Page<ResumoLancamento> resumir(
        LancamentoFilter lancamentoFilter,
        Pageable pageable );

}
