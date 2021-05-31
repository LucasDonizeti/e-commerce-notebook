package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.log.service;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.log.LogVenda;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.log.LogVendaAcao;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.log.persistencia.LogVendaDAO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


/**
 * author LucasDonizeti
 */

@Service
public class LogVendaService {

    @Autowired
    private LogVendaDAO logVendaDAO;

    public List<LogVenda> findLogByDate(String inicio, String fim){
        return logVendaDAO.findVendasByData(inicio, fim);
    }

    public void registrarLog(Produto produto, int quantidade, LogVendaAcao logVendaAcao){
        LogVenda logVenda=new LogVenda();
        logVenda.setData(LocalDate.now());
        logVenda.setProduto(produto);
        logVenda.setQuantidade(quantidade);
        logVenda.setAcao(logVendaAcao);
        logVendaDAO.save(logVenda);
    }
}
