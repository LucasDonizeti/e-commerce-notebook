package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.servico;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Precificacao;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.repositorio.PrecificacaoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * author LucasDonizeti
 */
@Service
public class PrecificacaoService {
    private final PrecificacaoDAO precificacaoDAO;

    @Autowired
    public PrecificacaoService(PrecificacaoDAO precificacaoDAO) {
        this.precificacaoDAO = precificacaoDAO;
    }

    public Precificacao save(Precificacao precificacao){
        return precificacaoDAO.save(precificacao);
    }

    public Optional<Precificacao> findById(UUID id){
        return precificacaoDAO.findById(id);
    }

    public List<Precificacao> findAll(){
        return precificacaoDAO.findAll();
    }
}
