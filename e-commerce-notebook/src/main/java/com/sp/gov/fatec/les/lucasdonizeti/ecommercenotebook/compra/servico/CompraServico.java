package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.servico;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.persistencia.CompraDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author LucasDonizeti
 */
@Service
public class CompraServico {
    private final CompraDAO compraDAO;

    @Autowired
    public CompraServico(CompraDAO compraDAO) {
        this.compraDAO = compraDAO;
    }
}
