package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.Cliente;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.config.EntidadeDominio;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.Cupom;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Produto;
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
@Entity(name = "_compra")
@SQLDelete(sql = "update _compra set habilitado = 0 where id = ?")
public class Compra extends EntidadeDominio implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Item> itens=new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    public Frete frete;

    @ManyToOne
    @JsonIgnore
    public Cliente cliente;

    @Column(name = "status", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pagamento> pagamentos=new ArrayList<>();

    @OneToMany
    private List<Cupom> cupomsDeTroca=new ArrayList<>();

    @OneToOne
    private Cupom cupomPromocional;

    public Float getValorDeCompra(){
        float valorDeCompraFinal=0;
        for (Item i : itens){
            valorDeCompraFinal+=i.getProduto().getPrecoDeVenda();
        }
        valorDeCompraFinal+=frete.getValor();

        return valorDeCompraFinal;
    }

    public Float getTotalPago(){
        float totalPago=0;
        for (Pagamento p:pagamentos)
            totalPago+=p.getValor();


        for (Cupom c:cupomsDeTroca)
            totalPago+=c.getValor();

        if (cupomPromocional!=null)
            totalPago+=cupomPromocional.getValor();

        return totalPago;
    }


}
