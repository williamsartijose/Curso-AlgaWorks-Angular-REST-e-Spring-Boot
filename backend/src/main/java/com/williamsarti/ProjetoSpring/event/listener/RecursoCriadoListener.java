package com.williamsarti.ProjetoSpring.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.williamsarti.ProjetoSpring.event.RecursoCriadoEvent;

@Component
public class RecursoCriadoListener
    implements
        ApplicationListener<RecursoCriadoEvent>
{

    @Override
    public void onApplicationEvent(
        RecursoCriadoEvent recursoCriadoEvent )
    {
        final HttpServletResponse response = recursoCriadoEvent.getResponse();
        final Long codigo = recursoCriadoEvent.getCodigo();

        adicionarHeaderLocation( response, codigo );
    }

    private void adicionarHeaderLocation(
        HttpServletResponse response,
        Long codigo )
    {
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path( "/{codigo}" )
            .buildAndExpand( codigo ).toUri();
        response.setHeader( "Location", uri.toASCIIString() );
    }

}