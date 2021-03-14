package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco;

/**
 * author LucasDonizeti
 */
public enum TipoResidencia {
    APARTAMENTO("Apartamento"), CASA("Casa");


    private String descricao;

    TipoResidencia(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao() {
        return descricao;
    }
}
