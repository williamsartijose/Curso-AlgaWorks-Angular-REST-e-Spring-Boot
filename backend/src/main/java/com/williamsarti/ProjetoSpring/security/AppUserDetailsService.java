package com.williamsarti.ProjetoSpring.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.williamsarti.ProjetoSpring.model.Usuario;
import com.williamsarti.ProjetoSpring.repository.UsuarioRepository;

@Service
public class AppUserDetailsService
    implements
        UserDetailsService
{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(
        String email )
        throws UsernameNotFoundException
    {
        final Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail( email );
        final Usuario usuario = usuarioOptional.orElseThrow( () -> new UsernameNotFoundException( "Usuário e/ou senha incorretos" ) );
        return new User( email, usuario.getSenha(), getPermissoes( usuario ) );
    }

    private Collection<? extends GrantedAuthority> getPermissoes(
        Usuario usuario )
    {
        final Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        usuario.getPermissoes().forEach( p -> authorities.add( new SimpleGrantedAuthority( p.getDescricao().toUpperCase() ) ) );
        return authorities;
    }

}