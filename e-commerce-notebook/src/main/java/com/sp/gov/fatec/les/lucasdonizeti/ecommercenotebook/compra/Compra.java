package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.Cliente;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.config.EntidadeDominio;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.CupomPromocional;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.CupomTroca;
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

    @ManyToOne
    public Cliente cliente;

    @Column(name = "status", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pagamento> pagamentos=new ArrayList<>();

    @OneToMany
    private List<CupomTroca> cupomsDeTroca=new ArrayList<>();

    @Column(name = "valor_de_compra")
    private Float valorDeCompra;

    @Column(name = "total_pago")
    private Float totalPago;

    @OneToOne(optional = true)
    private CupomPromocional cupomPromocional;

    public void calcularValores(){
        calcValorDeCompra();
        calcTotalPago();
    }

    public void calcValorDeCompra(){
        float valorDeCompraFinal=0;
        for (Item i : itens){
            valorDeCompraFinal+=i.getTotalItem();
        }
        valorDeCompra=valorDeCompraFinal;
    }

    public void calcTotalPago(){
        float totalPago=0;
        for (Pagamento p:pagamentos)
            totalPago+=p.getValor();

        for (CupomTroca c:cupomsDeTroca)
            totalPago+=c.getValor();

        if (cupomPromocional!=null)
            totalPago+=cupomPromocional.getValor();

        this.totalPago=totalPago;
    }


}
