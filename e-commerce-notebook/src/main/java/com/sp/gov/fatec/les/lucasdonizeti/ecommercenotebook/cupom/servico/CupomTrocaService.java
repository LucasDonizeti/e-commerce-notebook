package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.servico;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.CupomPromocional;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.CupomTroca;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.persistencia.CupomPromocionalDAO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.persistencia.CupomTrocaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * author LucasDonizeti
 */

@Service
public class CupomTrocaService {
    private final CupomTrocaDAO cupomTrocaDAO;

    @Autowired
    public CupomTrocaService(CupomTrocaDAO cupomPromocionalDAO) {
        this.cupomTrocaDAO = cupomPromocionalDAO;
    }

    public CupomTroca save(CupomTroca cupom) {
        return cupomTrocaDAO.save(cupom);
    }

    public List<CupomTroca> findByClienteId(UUID hash) {
        return cupomTrocaDAO.findByClienteId(hash);
    }

    public List<CupomTroca> findByClienteIdAndHabilitadoTrue(UUID hash) {
        return cupomTrocaDAO.findByClienteIdAndHabilitadoTrue(hash);
    }

    public Optional<CupomTroca> findById(UUID id) {
        return cupomTrocaDAO.findById(id);
    }
}
