package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.servico;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.Usuario;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.persistencia.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return usuarioDAO.save(usuario);
    }

}