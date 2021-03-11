package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao;


/**
 * author LucasDonizeti
 */

public enum Bandeira{
    MASTERCARD("MasterCard", "https://www.4devs.com.br/imagens/cc/logo_master.jpg"),
    VISA("Visa", "https://www.4devs.com.br/imagens/cc/logo_visa.jpg"),
    AMARICANEXPRESS("American Express", "https://www.4devs.com.br/imagens/cc/logo_amex.jpg"),
    DINERS("Diners Club", "https://www.4devs.com.br/imagens/cc/logo_diners.jpg"),
    DISCOVER("Discover", "https://www.4devs.com.br/imagens/cc/logo_discover.jpg"),
    ENROUTE("enRoute", "https://www.4devs.com.br/imagens/cc/logo_enroute.jpg"),
    JCB("JCB", "https://www.4devs.com.br/imagens/cc/logo_jcb.jpg"),
    VOYAGER("Voyager", "https://www.4devs.com.br/imagens/cc/logo_voyager.jpg"),
    HYPERCARD("HyperCard", "https://www.4devs.com.br/imagens/cc/logo_hiper.jpg"),
    AURA("Aura", "https://www.4devs.com.br/imagens/cc/logo_aura.jpg");

    private String imagem;
    private String descricao;

    Bandeira(String descricao, String imagem) {
        this.descricao = descricao;
        this.imagem = imagem;
    }
    public String getDescricao() {
        return descricao;
    }
    public String getImagem() {
        return imagem;
    }
}
