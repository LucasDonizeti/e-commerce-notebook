package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao.Cartao;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.config.EntidadeDominio;
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
@Entity(name = "_pagamento")
public class Pagamento extends EntidadeDominio implements Serializable {
    @Column(name = "valor")
    private Float valor;

    @ManyToOne
    private Cartao cartao;

    @ManyToOne
    private Compra compra;

}
