package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.Cliente;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.config.EntidadeDominio;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.Cupom;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Produto;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * author LucasDonizeti
 */
@Getter
@Setter
@Entity(name = "_compra")
public class Compra extends EntidadeDominio implements Serializable {
    @ManyToMany
    public List<Produto> produtos;

    @OneToOne
    public Frete frete;

    @ManyToOne
    public Cliente cliente;

    @OneToMany
    private List<Pagamento> pagamentos;

    @OneToMany
    private List<Cupom> cupoms;

    public Float getValorDeCompra(){
        float valorDeCompraFinal=0;
        for (Produto p : produtos){
            valorDeCompraFinal+=p.getPrecoDeVenda();
        }

        return valorDeCompraFinal;
    }

    public Float getTotalPago(){
        float totalPago=0;
        for (Pagamento p:pagamentos)
            totalPago+=p.getValor();


        for (Cupom c:cupoms)
            totalPago+=c.getValor();

        return totalPago;
    }
}
