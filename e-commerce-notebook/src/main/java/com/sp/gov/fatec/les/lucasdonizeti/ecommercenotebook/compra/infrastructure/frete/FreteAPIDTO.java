package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.infrastructure.frete;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Frete;
import lombok.Getter;
import lombok.Setter;

/**
 * author LucasDonizeti
 */
@Getter
@Setter
public class FreteAPIDTO {
    private String Codigo;
    private String Valor;
    private String PrazoEntrega;
    private String ValorSemAdicionais;
    private String ValorMaoPropria;
    private String ValorAvisoRecebimento;
    private String ValorValorDeclarado;
    private String EntregaDomiciliar;
    private String EntregaSabado;
    private String obsFim;
    private String Erro;
    private String MsgErro;

    public String logDetalhesFrete() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("------- Correios -------");
        stringBuilder.append("\n Codigo: " + getCodigo());
        stringBuilder.append("\n Valor: R$" + getValor());
        stringBuilder.append("\n PrazoEntrega: " + getPrazoEntrega() + " Dias");
        stringBuilder.append("\n Valor Sem Adicionais: R$" + getValorSemAdicionais());
        stringBuilder.append("\n Valor Mão Própria: R$" + getValorMaoPropria());
        stringBuilder.append("\n Valor Aviso Recebimento: R$" + getValorAvisoRecebimento());
        stringBuilder.append("\n Valor Declarado: R$" + getValorValorDeclarado());
        stringBuilder.append("\n Entrega Domiciliar: " + (getEntregaDomiciliar().equals("S") ? "Sim" : "Não"));
        stringBuilder.append("\n Entrega Sábado: " + (getEntregaSabado().equals("S") ? "Sim" : "Não"));
        if (!getObsFim().isBlank())
            stringBuilder.append("\n Obs: " + getObsFim());
        if (!getErro().isBlank() && !getErro().equals("0"))
            stringBuilder.append("\n Erro: " + getErro());
        if (!getMsgErro().isBlank())
            stringBuilder.append("\n Mensagem de Erro: " + getMsgErro());
        stringBuilder.append("\n------------------------");

        return stringBuilder.toString();
    }

    public static Frete mergeFrete(FreteAPIDTO freteAPIDTO, Frete frete) {
        if (freteAPIDTO.getValor() != null)
            if (!freteAPIDTO.getValor().isBlank())
                frete.setValor(Float.parseFloat(freteAPIDTO.getValor().replace(",", ".")));
        return frete;
    }
}
