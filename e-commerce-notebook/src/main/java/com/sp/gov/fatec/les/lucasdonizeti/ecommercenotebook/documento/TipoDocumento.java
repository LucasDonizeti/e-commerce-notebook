package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento;

/**
 * author LucasDonizeti
 */
public enum TipoDocumento {
    RG("RG"),
    CPF("CPF"),
    CNPJ("CNPJ");

    private String descricao;

    TipoDocumento(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao() {
        return descricao;
    }
}
