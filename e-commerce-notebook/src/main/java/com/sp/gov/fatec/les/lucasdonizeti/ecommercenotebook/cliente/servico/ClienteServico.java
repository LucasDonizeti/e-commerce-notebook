package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.servico;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.Cliente;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.persistencia.ClienteDAO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.config.Servico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

/**
 * author LucasDonizeti
 */
@Service
public class ClienteServico implements Servico<Cliente, Long> {
    private final ClienteDAO clienteDAO;

    @Autowired
    public ClienteServico(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    @Override
    public Cliente save(Cliente object) {
        if (clienteDAO.existsByEmail(object.getEmail()))
            throw new IllegalArgumentException("");
        return clienteDAO.save(object);

    }

    @Override
    public void delete(Long id) {
        Optional<Cliente> clienteOptional = clienteDAO.findById(id);
        if (clienteOptional.isPresent())
            clienteDAO.delete(clienteOptional.get());
        else
            throw new EntityNotFoundException("");
    }

    @Override
    public Cliente put(Cliente object) {
        if (clienteDAO.existsByEmail(object.getEmail()))
            return clienteDAO.save(object);
        else
            throw new EntityNotFoundException("");
    }

    @Override
    public Cliente findById(Long id) {
        Optional<Cliente> clienteOptional = clienteDAO.findById(id);
        if (clienteOptional.isPresent())
            return clienteOptional.get();
        throw new EntityNotFoundException("");
    }

    @Override
    public List<Cliente> findAll() {
        return clienteDAO.findAll();
    }
}
