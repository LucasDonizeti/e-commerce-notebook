package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.infrastructure.frete;

import com.thoughtworks.xstream.XStream;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

/**
 * author LucasDonizeti
 */
public class FreteClientAPI {
    private static final String nCdEmpresa = "";
    private static final String nDsSenha = "";
    private static final String nCdServico = "04014";
    private static final String nVlPeso = "2.25";
    private static final String nCdFormato = "1";
    private static final String nVlComprimento = "26";
    private static final String nVlAltura = "25";
    private static final String nVlLargura = "40";
    private static final String nVlDiametro = "0";
    private static final String sCdMaoPropria = "n";
    private static final String sCdAvisoRecebimento = "s";

    public static FreteAPIDTO findFreteByCorreios(String nCdEmpresa, String nDsSenha, String nCdServico, String sCepOrigem, String sCepDestino, String nVlPeso, String nCdFormato, String nVlComprimento, String nVlAltura, String nVlLargura, String nVlDiametro, String sCdMaoPropria, String nVlValorDeclarado, String sCdAvisoRecebimento) {
        RestTemplate restTemplate = new RestTemplateBuilder().build();
        String url = "http://ws.correios.com.br/calculador/CalcPrecoPrazo.aspx?nCdEmpresa=" + nCdEmpresa + "&sDsSenha=" + nDsSenha + "&sCepOrigem=" + sCepOrigem + "&sCepDestino=" + sCepDestino + "&nVlPeso=" + nVlPeso + "&nCdFormato=" + nCdFormato + "&nVlComprimento=" + nVlComprimento + "&nVlAltura=" + nVlAltura + "&nVlLargura=" + nVlLargura + "&sCdMaoPropria=" + sCdMaoPropria + "&nVlValorDeclarado=" + nVlValorDeclarado + "&sCdAvisoRecebimento=" + sCdAvisoRecebimento + "&nCdServico=" + nCdServico + "&nVlDiametro=" + nVlDiametro + "&StrRetorno=xml&nIndicaCalculo=3";
        String response = restTemplate.getForObject(url, String.class);
        return xmlToFreteAPIDTO(response);
    }

    public static FreteAPIDTO findFreteByCorreios(String sCepOrigem, String sCepDestino, Float nVlValorDeclarado) {
        RestTemplate restTemplate = new RestTemplateBuilder().build();
        String url = "http://ws.correios.com.br/calculador/CalcPrecoPrazo.aspx?nCdEmpresa=" + nCdEmpresa + "&sDsSenha=" + nDsSenha + "&sCepOrigem=" + sCepOrigem + "&sCepDestino=" + sCepDestino + "&nVlPeso=" + nVlPeso + "&nCdFormato=" + nCdFormato + "&nVlComprimento=" + nVlComprimento + "&nVlAltura=" + nVlAltura + "&nVlLargura=" + nVlLargura + "&sCdMaoPropria=" + sCdMaoPropria + "&nVlValorDeclarado=" + String.valueOf((nVlValorDeclarado>10000?10000:nVlValorDeclarado)) + "&sCdAvisoRecebimento=" + sCdAvisoRecebimento + "&nCdServico=" + nCdServico + "&nVlDiametro=" + nVlDiametro + "&StrRetorno=xml&nIndicaCalculo=3";
        System.out.println(url);
        String response = restTemplate.getForObject(url, String.class);
        return xmlToFreteAPIDTO(response);
    }

    private static FreteAPIDTO xmlToFreteAPIDTO(String response) {
        System.out.println(response);
        XStream xStream = new XStream();
        xStream.alias("Servicos", Servicos.class);
        xStream.alias("cServico", FreteAPIDTO.class);
        Servicos servicos = (Servicos) xStream.fromXML(response);
        return servicos.getcServico();
    }

    private class Servicos {
        FreteAPIDTO cServico;

        public FreteAPIDTO getcServico() {
            return cServico;
        }

        public void setcServico(FreteAPIDTO cServico) {
            this.cServico = cServico;
        }
    }

    public static void main(String[] args) {
        FreteAPIDTO freteAPIDTO = FreteClientAPI.findFreteByCorreios("01153000", "08940000", 9000f);
        System.out.println(freteAPIDTO.logDetalhesFrete());
    }
}
/*
        EX:
        http://ws.correios.com.br/calculador/CalcPrecoPrazo.aspx?nCdEmpresa=08082650&sDsSenha=564321&sCepOrigem=70002900&sCepDestino=04547000&nVlPeso=1&nCdFormato=1&nVlComprimento=20&nVlAltura=20&nVlLargura=20&sCdMaoPropria=n&nVlValorDeclarado=0&sCdAvisoRecebimento=n&nCdServico=04510&nVlDiametro=0&StrRetorno=xml&nIndicaCalculo=3

        Dados

        String:nCdEmpresa
        //caso não tenha contrato informar vazio

        String:sDsSenha
        //caso não tenha contrato informar vazio

        String:nCdServico
        //04014-SEDEX
        //04510-PAC
        //04782-SEDEX 12
        //04790-SEDEX 10
        //04804-SEDEX Hoje

        String:sCepOrigem
        //CEP de Origem sem hífen.Exemplo: 08773600

        String:sCepDestino
        //CEP de Origem sem hífen.Exemplo: 08940000

        String:nVlPeso
        //Peso da encomenda, incluindo sua embalagem. O
        //peso deve ser informado em quilogramas. Se o
        //formato for Envelope, o valor máximo permitido será 1
        //kg

        Integer:nCdFormato
        //1 – Formato caixa/pacote
        //2 – Formato rolo/prisma
        //3 - Envelope

        Decimal:nVlComprimento
        //Comprimento da encomenda (incluindo embalagem), em centímetros.

        Decimal:nVlAltura
        //Altura da encomenda (incluindo embalagem), em centímetros.
        //Se o formato for envelope, informar zero(0)

        Decimal:nVlLargura
        //Largura da encomenda (incluindo embalagem), em centímetros.

        Decimal:nVlDiametro
        //Diâmetro da encomenda (incluindo embalagem), em centímetros.

        String:sCdMaoPropria
        //Indica se a encomenda será entregue com o serviço adicional mão própria.
        //Valores possíveis: S ou N (S – Sim, N – Não)

        Decimal:nVlValorDeclarado
        //Indica se a encomenda será entregue com o serviço
        //adicional valor declarado. Neste campo deve ser
        //apresentado o valor declarado desejado, em Reais

        String:sCdAvisoRecebimento
        //Indica se a encomenda será entregue com o serviço
        //adicional aviso de recebimento.
        //Valores possíveis: S ou N (S – Sim, N – Não)


        Exemplo de Retorno
        <Servicos>
            <cServico>
            <Codigo>04510</Codigo>
            <Valor>27,80</Valor>
            <PrazoEntrega>12</PrazoEntrega>
            <ValorSemAdicionais>27,80</ValorSemAdicionais>
            <ValorMaoPropria>0,00</ValorMaoPropria>
            <ValorAvisoRecebimento>0,00</ValorAvisoRecebimento>
            <ValorValorDeclarado>0,00</ValorValorDeclarado>
            <EntregaDomiciliar>S</EntregaDomiciliar>
            <EntregaSabado>N</EntregaSabado>
            <obsFim/>
            <Erro>0</Erro>
            <MsgErro/>
            </cServico>
        </Servicos>


        Erros:
        0  : Processamento com sucesso
        -1 : Código de serviço inválido
        -2 : CEP de origem inválido
        -3 : CEP de destino inválido
        -4 : Peso excedido
        -5 : O Valor Declarado não deve exceder R$ 10.000,00
        -6 : Serviço indisponível para o trecho informado
        -7 : O Valor Declarado é obrigatório para este serviço
        -8 : Este serviço não aceita Mão Própria
        -9 : Este serviço não aceita Aviso de Recebimento
        -10: Precificação indisponível para o trecho informado
        -11: Para definição do preço deverão ser informados, também, o comprimento, a
        largura e altura do objeto em centímetros (cm).
        -12: Comprimento inválido.
        -13: Largura inválida.
        -14: Altura inválida.
        -15: O comprimento não pode ser maior que 105 cm.
        -16: A largura não pode ser maior que 105 cm.
        -17: A altura não pode ser maior que 105 cm.
        -18: A altura não pode ser inferior a 2 cm.
        -20: A largura não pode ser inferior a 11 cm.
        -22: O comprimento não pode ser inferior a 16 cm.
        -23: A soma resultante do comprimento + largura + altura não deve superar a 200 cm.
        -24: Comprimento inválido.
        -25: Diâmetro inválido
        -26: Informe o comprimento.
        -27: Informe o diâmetro.
        -28: O comprimento não pode ser maior que 105 cm.
        -29: O diâmetro não pode ser maior que 91 cm.
        -30: O comprimento não pode ser inferior a 18 cm.
        -31: O diâmetro não pode ser inferior a 5 cm.
        -32: A soma resultante do comprimento + o dobro do diâmetro não deve superar a
        200: cm.
        -33: Sistema temporariamente fora do ar. Favor tentar mais tarde.
        -34: Código Administrativo ou Senha inválidos.
        12/16
        DEENC – Departamento de Encomendas
        Revisão 25/09/2019
        -35: Senha incorreta.
        -36: Cliente não possui contrato vigente com os Correios.
        -37: Cliente não possui serviço ativo em seu contrato.
        -38: Serviço indisponível para este código administrativo.
        -39: Peso excedido para o formato envelope
        -40: Para definicao do preco deverao ser informados, tambem, o comprimento e a
        largura e altura do objeto em centimetros (cm).
        -41: O comprimento nao pode ser maior que 60 cm.
        -42: O comprimento nao pode ser inferior a 16 cm.
        -43: A soma resultante do comprimento + largura nao deve superar a 120 cm.
        -44: A largura nao pode ser inferior a 11 cm.
        -45: A largura nao pode ser maior que 60 cm.
        -888: Erro ao calcular a tarifa
        006: Localidade de origem não abrange o serviço informado
        007: Localidade de destino não abrange o serviço informado
        008: Serviço indisponível para o trecho informado
        009: CEP inicial pertencente a Área de Risco.
        010: CEP de destino está temporariamente sem entrega domiciliar. A entrega será
        efetuada na agência indicada no Aviso de Chegada que será entregue no
        endereço do destinatário
        011: CEP de destino está sujeito a condições especiais de entrega pela ECT e será
        realizada com o acréscimo de até 7 (sete) dias úteis ao prazo regular.
        7 :Serviço indisponível, tente mais tarde
        99: Outros erros diversos do .Net
        */
