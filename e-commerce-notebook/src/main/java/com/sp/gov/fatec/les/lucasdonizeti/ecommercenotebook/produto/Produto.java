package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.config.EntidadeDominio;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.Notebook;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * author LucasDonizeti
 */
@Getter
@Setter
@Entity(name = "_produto")
@SQLDelete(sql = "update _produto set habilitado = 0 where id = ?")
public class Produto extends EntidadeDominio implements Serializable {
    @Column(name = "custo")
    private Float custo;

    @Column(name = "estoque")
    private int estoque;

    @Column(name = "pontuacao_cliente")
    private int pontuacaoCliente;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private  List<Imagem> imagemList=new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Notebook notebook;

    @ManyToOne
    private Precificacao precificacao;

    public void addImagem(Imagem i){
        this.imagemList.add(i);
    }

    public Float getPrecoDeVenda(){
        if (precificacao != null)
            return custo + custo*precificacao.getMargemDeLucro();
        return custo;
    }
}
