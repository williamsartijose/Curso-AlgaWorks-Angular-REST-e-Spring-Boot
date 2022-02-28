package com.williamsarti.ProjetoSpring.repository.lancamento;

import java.util.List;

import com.williamsarti.ProjetoSpring.model.Lancamento;
import com.williamsarti.ProjetoSpring.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery
{

    public List<Lancamento> filtrar(
        LancamentoFilter lancamentoFilter );

}