package exceptionHandler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AlgamoneyExceptionHandler
    extends
        ResponseEntityExceptionHandler
{

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
        HttpMessageNotReadableException ex,
        HttpHeaders headers,
        HttpStatus status,
        WebRequest request )
    {

        final String mensagemUsuario = messageSource.getMessage( "mensagem.invalida", null, LocaleContextHolder.getLocale() );
        final String mensagemDesenvolvedor = ex.getCause().toString();
        return handleExceptionInternal( ex, new Erro( mensagemUsuario, mensagemDesenvolvedor ), headers, HttpStatus.BAD_REQUEST, request );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex,
        HttpHeaders headers,
        HttpStatus status,
        WebRequest request )
    {

        final List<Erro> erros = criarListaDeErros( ex.getBindingResult() );
        return handleExceptionInternal( ex, erros, headers, HttpStatus.BAD_REQUEST, request );
    }

    private List<Erro> criarListaDeErros(
        BindingResult bindingResult )
    {
        final List<Erro> erros = new ArrayList<>();

        for( final FieldError fieldError : bindingResult.getFieldErrors() ) {
            final String mensagemUsuario = messageSource.getMessage( fieldError, LocaleContextHolder.getLocale() );
            final String mensagemDesenvolvedor = fieldError.toString();
            erros.add( new Erro( mensagemUsuario, mensagemDesenvolvedor ) );
        }

        return erros;
    }

    public static class Erro
    {

        private final String mensagemUsuario;
        private final String mensagemDesenvolvedor;

        public Erro(
            String mensagemUsuario,
            String mensagemDesenvolvedor )
        {
            this.mensagemUsuario = mensagemUsuario;
            this.mensagemDesenvolvedor = mensagemDesenvolvedor;
        }

        public String getMensagemUsuario()
        {
            return mensagemUsuario;
        }

        public String getMensagemDesenvolvedor()
        {
            return mensagemDesenvolvedor;
        }

    }

}