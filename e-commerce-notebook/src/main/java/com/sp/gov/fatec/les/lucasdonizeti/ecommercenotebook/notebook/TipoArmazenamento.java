package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook;

/**
 * author LucasDonizeti
 */
public enum TipoArmazenamento {
    HD("HD"),
    SSD("SSD");

    private String descricao;

    TipoArmazenamento(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao() {
        return descricao;
    }
}
