package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario;

/**
 * author LucasDonizeti
 */


public enum TipoUsuario {
    ADMINISTRADOR("Administrador"),
    CLIENTE("Cliente");

    private String descricao;

    TipoUsuario(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao() {
        return descricao;
    }

}
