package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente;

/**
 * author LucasDonizeti
 */
public enum TipoCliente {
    FISICA("Fisica"),
    JURIDICA("Juridica");

    private String descricao;

    TipoCliente(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao() {
        return descricao;
    }
}
