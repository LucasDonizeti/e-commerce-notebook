package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco;

/**
 * author LucasDonizeti
 */
public enum Longradouro {
    AEROPORTO(";aeroporto"), ALAMEDA("alameda"), AREA("area"),
    AVENIDA("avenida"), CAMPO("campo"), CHACARA("chácara"),
    CONDOMINIO("condominio"), CONJUNTO("conjunto"), DISTRITO("distrito"),
    ESPLANADA("esplanada"), ESTACAO("estacao"), ESTRADA("estrada"),
    FAVELA("favela"), FAZENDA("fazenda"), FEIRA("feira"),
    JARDIM("jandim"), LAGO("lago"), LARGO("largo"), MORRO("morro"),
    NUCLEO("nucleo"), PARQUE("parque"), PRACA("praca"), RECANTO("recanto"),
    RESIDENCIAL("residencial"), RODOVIA("rodovia"), RUA("rua"), SITIO("sitio"),
    TREVO("trevo"), VALE("vale"), VIA("via"), VIADUTO("viaduto"),
    VIELA("viela"), VILA("vila");


    private String descricao;

    Longradouro(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao() {
        return descricao;
    }
}
