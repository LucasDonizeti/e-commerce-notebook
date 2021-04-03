package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.servico;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.Cupom;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.persistencia.CupomDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * author LucasDonizeti
 */
@Service
public class CupomServico {
    private final CupomDAO cupomRepository;

    @Autowired
    public CupomServico(CupomDAO cupomRepository) {
        this.cupomRepository = cupomRepository;
    }

    public Cupom save(Cupom cupom){
        return cupomRepository.save(cupom);
    }

    public List<Cupom> findByClienteId(UUID hash){
        return cupomRepository.findByClienteId(hash);
    }
}
