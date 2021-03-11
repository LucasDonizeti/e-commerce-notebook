package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook;

/**
 * author LucasDonizeti
 */
public enum Categoria {
    BASICO("Basico"),
    ENTRADA("entrada"),
    INTERMEDIARIO("Intermediario"),
    PROFISSIONAL("Profissional"),
    GAMER("Gamer"),
    PREMIUM("Premium"),
    MAC("Mac"),
    NOTEBOOK2EM1("2 em 1");

    private String descricao;

    Categoria(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao() {
        return descricao;
    }
}
