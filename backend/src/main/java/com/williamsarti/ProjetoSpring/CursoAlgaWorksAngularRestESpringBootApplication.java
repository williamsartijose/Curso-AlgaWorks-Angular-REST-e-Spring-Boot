package com.williamsarti.ProjetoSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.williamsarti.ProjetoSpring.config.property.AlgamoneyApiProperty;

@SpringBootApplication
@EnableConfigurationProperties( AlgamoneyApiProperty.class )
public class CursoAlgaWorksAngularRestESpringBootApplication
{

    public static void main(
        String[] args )
    {
        SpringApplication.run( CursoAlgaWorksAngularRestESpringBootApplication.class, args );
    }

}
