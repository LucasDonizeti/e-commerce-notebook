package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco;

/**
 * author LucasDonizeti
 */
public enum TipoResidencia {
    APARTAMENTO("Movel"), CASA("Fixo");


    private String descricao;

    TipoResidencia(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao() {
        return descricao;
    }
}
