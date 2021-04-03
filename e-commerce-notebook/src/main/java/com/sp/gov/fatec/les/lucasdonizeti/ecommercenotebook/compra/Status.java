package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra;

/**
 * author LucasDonizeti
 */
public enum Status {
    REPROVADA("Reprovada", "Yellow"),//ao fazer a compra
    APROVADA("Aprovada", "Green"),//ao validar metodo de pagamento
    EM_PROCESSAMENTO("Em Processamento", "Yellow"),//ao administrador autorizar a venda
    EM_TRANSITO("Em trânsito", "Orange"),//ao ser selecionada para ser entregue por um administrador
    ENTREGUE("Entregue", "Blue"),//após a compra ter sido entregue por um administrador
    EM_TROCA("Em troca", "Yellow"),//ao cliente pedir a troca do produto
    TROCA_AUTORIZADA("Troca Autorizada", "Green"),//ao administrador autorizar a troca do produto
    TROCA_NAO_AUTORIZADA("Troca Não Autorizada", "Orange"),//ao administrador nao autorizar a troca do produto
    TROCA_CONCLUIDA("Troca Concluida", "Blue");//ao administrador confirmar a troca do produto

    private String descricao;
    private String color;

    Status(String descricao, String color) {
        this.descricao = descricao;
        this.color=color;
    }
    public String getDescricao() {
        return descricao;
    }
    public String getColor() {
        return color;
    }
}
