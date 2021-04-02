package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.config.security;


import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.Usuario;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.servico.UsuarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * author LucasDonizeti
 */
@Service
public class UserDetailsServiceImp implements UserDetailsService {
    private final UsuarioServico usuarioServico;

    @Autowired
    public UserDetailsServiceImp(UsuarioServico usuarioServico) {
        this.usuarioServico = usuarioServico;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional=usuarioServico.findByLogin(s);
        if (usuarioOptional.isPresent()) {
            System.out.println("username: " + usuarioOptional.get().getUsername());
            System.out.println("password: " + usuarioOptional.get().getPassword());
            return usuarioOptional.get();
        }
        else throw new UsernameNotFoundException("Email não existe");
    }
}
