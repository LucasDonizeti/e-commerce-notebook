package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.servico;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.Usuario;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.persistencia.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * author LucasDonizeti
 */

@Service
public class UsuarioServico {
    private final UsuarioDAO usuarioDAO;
    private Pattern BCRYPT_PATTERN = Pattern.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");

    @Autowired
    public UsuarioServico(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public Usuario save(Usuario usuario){
        if (!isEncoded(usuario.getSenha()))
            usuario.setSenha(BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt()));
        return usuarioDAO.save(usuario);
    }

    private Boolean isEncoded(String check) {
        return BCRYPT_PATTERN.matcher(check).matches();
    }

    public List<Usuario> findAll(){
        return usuarioDAO.findAll();
    }

    public Optional<Usuario> findByLogin(String login){
        return usuarioDAO.findByLogin(login);
    }

}

