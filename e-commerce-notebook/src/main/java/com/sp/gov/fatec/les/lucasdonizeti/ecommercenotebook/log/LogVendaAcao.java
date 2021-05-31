package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.log;

import lombok.Getter;

/**
 * author LucasDonizeti
 */

@Getter
public enum LogVendaAcao {
    VENDA("Venda"),
    DEVOLUCAO("Devolução");

    private String descricao;

    LogVendaAcao(String descricao){
        this.descricao=descricao;
    }
}
