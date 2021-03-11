package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook;

/**
 * author LucasDonizeti
 */
public enum SO {
    WINDOWS10HOME("Windows 10 Home Single Language"),
    WINDOWS10PRO("Windows 10 PRO"),
    LINUX("Linux"),
    MAC("MacOS"),
    NENHUM("Sem sistema operacional");

    private String descricao;

    SO(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao() {
        return descricao;
    }
}
