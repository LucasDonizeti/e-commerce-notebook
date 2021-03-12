package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.config.EntidadeDominio;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.Notebook;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.Serializable;

/**
 * author LucasDonizeti
 */
@Getter
@Setter
@Entity(name = "_produto")
public class Produto extends EntidadeDominio implements Serializable {
    @Column(name = "custo")
    private Float custo;

    @Column(name = "estoque")
    private int estoque;

    @Column(name = "pontuacao_cliente")
    private int pontuacaoCliente;

    @OneToOne
    @JsonIgnore
    private Notebook notebook;

    @ManyToOne
    private Precificacao precificacao;

    public Float getPrecoDeVenda(){
        if (precificacao != null)
            return custo + custo*precificacao.getMargemDeLucro();
        return custo;
    }
}
