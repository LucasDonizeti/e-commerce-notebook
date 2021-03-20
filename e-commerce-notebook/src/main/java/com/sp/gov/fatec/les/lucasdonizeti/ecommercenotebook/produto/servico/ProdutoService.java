package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.servico;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Produto;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.repositorio.ProdutoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * author LucasDonizeti
 */
@Service
public class ProdutoService {
    private final ProdutoDAO produtoDAO;
    private final PrecificacaoService precificacaoService;

    @Autowired
    public ProdutoService(ProdutoDAO produtoDAO, PrecificacaoService precificacaoService) {
        this.produtoDAO = produtoDAO;
        this.precificacaoService = precificacaoService;
    }

    public List<Produto> findAll(){
        return produtoDAO.findAll();
    }

    public Produto save(Produto produto){
        return produtoDAO.save(produto);
    }

    public Optional<Produto> findById(UUID id){
        return produtoDAO.findById(id);
    }

    public void inverterHabilitado(UUID id){
        Optional<Produto> produtoOptional=  produtoDAO.findById(id);
        if (produtoOptional.isPresent()){
            produtoOptional.get().setHabilitado(!produtoOptional.get().isHabilitado());
            produtoDAO.save(produtoOptional.get());
        }
    }

}


//como eu to fazendo sozinho ta sem refatoração
