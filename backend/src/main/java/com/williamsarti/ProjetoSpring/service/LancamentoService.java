package com.williamsarti.ProjetoSpring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.williamsarti.ProjetoSpring.exception.PessoaInexistenteOuInativaException;
import com.williamsarti.ProjetoSpring.model.Lancamento;
import com.williamsarti.ProjetoSpring.model.Pessoa;
import com.williamsarti.ProjetoSpring.repository.LancamentoRepository;
import com.williamsarti.ProjetoSpring.repository.PessoaRepository;

@Service
public class LancamentoService
{

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private LancamentoRepository lancamentoRepository;

    public Lancamento salvar(
        Lancamento lancamento )
    {
        final Pessoa pessoa = pessoaRepository.findOne( lancamento.getPessoa().getCodigo() );
        if( pessoa == null || pessoa.getAtivo() ) {
            throw new PessoaInexistenteOuInativaException();
        }

        return lancamentoRepository.save( lancamento );
    }

}
