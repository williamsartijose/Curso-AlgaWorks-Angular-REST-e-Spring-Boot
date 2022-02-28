package com.williamsarti.ProjetoSpring.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.williamsarti.ProjetoSpring.event.RecursoCriadoEvent;
import com.williamsarti.ProjetoSpring.exception.PessoaInexistenteOuInativaException;
import com.williamsarti.ProjetoSpring.model.Lancamento;
import com.williamsarti.ProjetoSpring.repository.LancamentoRepository;
import com.williamsarti.ProjetoSpring.repository.filter.LancamentoFilter;
import com.williamsarti.ProjetoSpring.service.LancamentoService;

import exceptionHandler.AlgamoneyExceptionHandler.Erro;

@RestController
@RequestMapping( "/lancamentos" )
public class LancamentoResource
{

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private LancamentoService lancamentoService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public List<Lancamento> pesquisar(
        LancamentoFilter lancamentoFilter )
    {
        return lancamentoRepository.filtrar( lancamentoFilter );
    }

    @GetMapping( "/{codigo}" )
    public ResponseEntity<Lancamento> buscarPeloCodigo(
        @PathVariable Long codigo )
    {
        final Lancamento lancamento = lancamentoRepository.findOne( codigo );
        return lancamento != null ? ResponseEntity.ok( lancamento ) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Lancamento> criar(
        @Valid @RequestBody Lancamento lancamento,
        HttpServletResponse response )
    {
        final Lancamento lancamentoSalvo = lancamentoService.salvar( lancamento );
        publisher.publishEvent( new RecursoCriadoEvent( this, response, lancamentoSalvo.getCodigo() ) );
        return ResponseEntity.status( HttpStatus.CREATED ).body( lancamentoSalvo );
    }

    @ExceptionHandler( {
        PessoaInexistenteOuInativaException.class
    } )
    public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(
        PessoaInexistenteOuInativaException ex )
    {
        final String mensagemUsuario = messageSource.getMessage( "pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale() );
        final String mensagemDesenvolvedor = ex.toString();
        final List<Erro> erros = Arrays.asList( new Erro( mensagemUsuario, mensagemDesenvolvedor ) );
        return ResponseEntity.badRequest().body( erros );
    }

}