package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.servico;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.Cliente;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.persistencia.ClienteDAO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento.Documento;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.servico.UsuarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * author LucasDonizeti
 */

@Service
public class ClienteServico {
    private final ClienteDAO clienteDAO;
    private final UsuarioServico usuarioServico;


    @Autowired
    public ClienteServico(ClienteDAO clienteDAO, UsuarioServico usuarioServico) {
        this.clienteDAO = clienteDAO;
        this.usuarioServico = usuarioServico;
    }

    public Cliente save(Cliente cliente) {
        cliente.getUsuario().setSenha(BCrypt.hashpw(cliente.getUsuario().getSenha(), BCrypt.gensalt()));
        cliente.setUsuario(usuarioServico.save(cliente.getUsuario()));
        return clienteDAO.save(cliente);
    }

    public List<Cliente> findAll() {
        return clienteDAO.findAll();
    }

    public void delete(Cliente cliente) {
        clienteDAO.delete(cliente);
    }

    public Optional<Cliente> findById(UUID id) {
        return clienteDAO.findById(id);
    }

    public Optional<Cliente> findByUsuarioId(UUID usuario){
        return clienteDAO.findByUsuarioId(usuario);
    }
}
