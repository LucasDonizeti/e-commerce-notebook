package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente;

/**
 * author LucasDonizeti
 */
public enum Genero {
    MASCULINO("Masculino"),
    FEMININO("Feminino");

    private String descricao;

    Genero(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao() {
        return descricao;
    }
}
