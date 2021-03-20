package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.servico;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.Endereco;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.persistencia.EnderecoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author LucasDonizeti
 */
@Service
public class EnderecoServico {
    private final EnderecoDAO enderecoDAO;

    @Autowired
    public EnderecoServico(EnderecoDAO enderecoDAO) {
        this.enderecoDAO = enderecoDAO;
    }

    public void remove(Endereco endereco){
        enderecoDAO.delete(endereco);
    }
}
