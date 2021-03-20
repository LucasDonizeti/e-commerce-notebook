package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao.servico;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao.Cartao;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao.persistencia.CartaoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author LucasDonizeti
 */
@Service
public class CartaoSarvice {
    private final CartaoDAO cartaoDAO;

    @Autowired
    public CartaoSarvice(CartaoDAO cartaoDAO) {
        this.cartaoDAO = cartaoDAO;
    }

    public void delete(Cartao cartao){
        cartaoDAO.delete(cartao);
    }
}
