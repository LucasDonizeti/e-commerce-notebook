package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.telefone;

/**
 * author LucasDonizeti
 */
public enum TipoTelefone {
    MOVEL("Móvel"),
    FIXO("Fixo");

    private String descricao;

    TipoTelefone(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao() {
        return descricao;
    }
}
