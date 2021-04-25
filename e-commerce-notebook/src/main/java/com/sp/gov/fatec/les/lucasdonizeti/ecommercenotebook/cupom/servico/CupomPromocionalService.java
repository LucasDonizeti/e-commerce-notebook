package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.servico;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.CupomPromocional;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.persistencia.CupomPromocionalDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public List<CupomPromocional> findAll(){
        return cupomPromocionalDAO.findAll();
    }

    public List<CupomPromocional> findHabilitado(){
        return cupomPromocionalDAO.findHabilitado();
    }

    public Optional<CupomPromocional> findById(UUID id){
        return cupomPromocionalDAO.findById(id);
    }

    public CupomPromocional save(CupomPromocional cupom){
        return cupomPromocionalDAO.save(cupom);
    }

    public void delete(CupomPromocional cupomPromocional){
        cupomPromocionalDAO.delete(cupomPromocional);
    }

    public void subtrairUso(UUID id){
        Optional<CupomPromocional> cupomPromocionalOptional=cupomPromocionalDAO.findById(id);
        if (cupomPromocionalOptional.isPresent()) {
            CupomPromocional cupomPromocional = cupomPromocionalOptional.get();
            cupomPromocional.setQuantidade(cupomPromocional.getQuantidade() - 1);
            cupomPromocionalDAO.save(cupomPromocional);
        }
    }

    public Optional<CupomPromocional> findUtilizavelByCodigo(String codigo){
        Optional<CupomPromocional> cupomPromocionalOptional = cupomPromocionalDAO.findByCodigo(codigo);
        if (cupomPromocionalOptional.isPresent()) {
            if (cupomPromocionalOptional.get().getQuantidade() > 0) {
                return cupomPromocionalOptional;
            }
        }
        return Optional.empty();
    }
}
