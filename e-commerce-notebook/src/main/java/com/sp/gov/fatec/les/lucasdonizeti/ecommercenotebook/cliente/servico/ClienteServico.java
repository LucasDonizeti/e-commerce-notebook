package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.servico;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.Cliente;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.persistencia.ClienteDAO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.servico.UsuarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Cliente save(Cliente cliente){
        if (cliente.getUsuario().getId() == null || cliente.getUsuario().getId() == 0){
            cliente.setUsuario(usuarioServico.save(cliente.getUsuario()));
        }

        return clienteDAO.save(cliente);
    }

    public List<Cliente> findAll(){
        return clienteDAO.findAll();
    }
}
