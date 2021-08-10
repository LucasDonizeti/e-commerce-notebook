package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.servico;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.Cliente;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.persistencia.ClienteDAO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.persistencia.ClientePersistencia;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.servico.CupomTrocaService;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.servico.UsuarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
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
    private final CupomTrocaService cupomServico;
    private final ClientePersistencia clientePersistencia;


    @Autowired
    public ClienteServico(ClienteDAO clienteDAO, UsuarioServico usuarioServico, CupomTrocaService cupomServico, ClientePersistencia clientePersistencia) {
        this.clienteDAO = clienteDAO;
        this.usuarioServico = usuarioServico;
        this.cupomServico = cupomServico;
        this.clientePersistencia = clientePersistencia;
    }

    public Cliente save(Cliente cliente) {
        //cliente.getUsuario().setSenha(BCrypt.hashpw(cliente.getUsuario().getSenha(), BCrypt.gensalt()));
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
        Optional<Cliente> cliente = clienteDAO.findById(id);
        if (cliente.isPresent())
            cliente.get().setCupomsDeTroca(cupomServico.findByClienteId(cliente.get().getId()));

        return cliente;
    }

    public Optional<Cliente> findByUsuarioId(UUID usuario){
        Optional<Cliente> cliente = clienteDAO.findByUsuarioId(usuario);
        if (cliente.isPresent())
            cliente.get().setCupomsDeTroca(cupomServico.findByClienteId(cliente.get().getId()));

        return cliente;
    }

    public Optional<Cliente> findByCompraID(UUID id){
        return clienteDAO.findByComprasId(id);
    }

    public void somarRank(UUID clienteID, int adc){
        System.out.println(" update _cliente as c set c.rank = (c.rank + " + adc +" ) where c.id = '" + clienteID +"' ");
        try {
            clientePersistencia.adcRank(clienteID, adc);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
