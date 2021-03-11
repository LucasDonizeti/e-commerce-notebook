package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom;

/**
 * author LucasDonizeti
 */
public enum TipoCupom {
    PROMOCIONAL("Promocional"),
        TROCA("troca");

    private String descricao;

    TipoCupom(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao() {
        return descricao;
    }
}
