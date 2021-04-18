package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.servico;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.CupomPromocional;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.persistencia.CupomPromocionalDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * author LucasDonizeti
 */

@Service
public class CupomPromocionalService {
    private final CupomPromocionalDAO cupomPromocionalDAO;

    @Autowired
    public CupomPromocionalService(CupomPromocionalDAO cupomPromocionalDAO) {
        this.cupomPromocionalDAO = cupomPromocionalDAO;
    }

    public CupomPromocional save(CupomPromocional cupom){
        return cupomPromocionalDAO.save(cupom);
    }

    public Optional<CupomPromocional> findByCodigo(String codigo){
        return cupomPromocionalDAO.findByCodigo(codigo);
    }
}
