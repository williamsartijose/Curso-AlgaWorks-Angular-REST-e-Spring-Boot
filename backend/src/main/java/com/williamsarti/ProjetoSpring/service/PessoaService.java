package com.williamsarti.ProjetoSpring.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.williamsarti.ProjetoSpring.model.Pessoa;
import com.williamsarti.ProjetoSpring.repository.PessoaRepository;

@Service
public class PessoaService
{

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa atualizar(
        Long codigo,
        Pessoa pessoa )
    {
        final Pessoa pessoaSalva = buscarPessoaPeloCodigo( codigo );

        BeanUtils.copyProperties( pessoa, pessoaSalva, "codigo" );
        return pessoaRepository.save( pessoaSalva );
    }

    public void atualizarPropriedadeAtivo(
        Long codigo,
        Boolean ativo )
    {
        final Pessoa pessoaSalva = buscarPessoaPeloCodigo( codigo );
        pessoaSalva.setAtivo( ativo );
        pessoaRepository.save( pessoaSalva );
    }

    public Pessoa buscarPessoaPeloCodigo(
        Long codigo )
    {
        final Pessoa pessoaSalva = pessoaRepository.findOne( codigo );
        if( pessoaSalva == null ) {
            throw new EmptyResultDataAccessException( 1 );
        }
        return pessoaSalva;
    }

}