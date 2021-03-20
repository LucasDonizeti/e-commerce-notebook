package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.service;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.Armazenamento;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.persistencia.ArmazenamentoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author LucasDonizeti
 */
@Service
public class ArmazenamentoService {
    private final ArmazenamentoDAO armazenamentoDAO;

    @Autowired
    public ArmazenamentoService(ArmazenamentoDAO armazenamentoDAO) {
        this.armazenamentoDAO = armazenamentoDAO;
    }

    public void delete(Armazenamento armazenamento){
        armazenamentoDAO.delete(armazenamento);
    }
}
