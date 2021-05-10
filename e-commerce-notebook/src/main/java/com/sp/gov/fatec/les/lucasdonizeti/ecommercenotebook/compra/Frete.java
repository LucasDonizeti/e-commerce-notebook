package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.config.EntidadeDominio;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.Endereco;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * author LucasDonizeti
 */

@Getter
@Setter
@Entity(name = "_frete")
public class Frete extends EntidadeDominio implements Serializable {
    @ManyToOne(optional = true)
    public Endereco endereco;

    @Column(name = "valor")
    private Float valor=0f;

    @OneToOne
    private Item item;
}
