package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.servico;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.Usuario;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.persistencia.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * author LucasDonizeti
 */

@Service
public class UsuarioServico {
    private final UsuarioDAO usuarioDAO;

    @Autowired
    public UsuarioServico(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public Usuario save(Usuario usuario){
        usuario.setSenha(BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt()));
        return usuarioDAO.save(usuario);
    }

    public List<Usuario> findAll(){
        return usuarioDAO.findAll();
    }

    public Optional<Usuario> findByLogin(String login){
        return usuarioDAO.findByLogin(login);
    }

}

