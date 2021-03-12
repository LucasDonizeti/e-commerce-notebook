package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra;

/**
 * author LucasDonizeti
 */
public enum Status {
    REPROVADA("Reprovada"),//ao fazer a compra
    APROVADA("Aprovada"),//ao validar metodo de pagamento
    EM_PROCESSAMENTO("Em Processamento"),//ao administrador autorizar a venda
    EM_TRANSITO("Em trânsito"),//ao ser selecionada para ser entregue por um administrador
    ENTREGUE("Entregue"),//após a compra ter sido entregue por um administrador
    EM_TROCA("Em troca"),//ao cliente pedir a troca do produto
    TROCA_AUTORIZADA("Troca Autorizada");//ao administrador autorizar a troca do produto

    private String descricao;

    Status(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao() {
        return descricao;
    }
}
