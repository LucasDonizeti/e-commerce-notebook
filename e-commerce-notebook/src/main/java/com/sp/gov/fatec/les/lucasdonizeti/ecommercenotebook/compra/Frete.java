package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.config.EntidadeDominio;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.Endereco;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;

/**
 * author LucasDonizeti
 */

@Getter
@Setter
@Entity(name = "_frete")
public class Frete extends EntidadeDominio implements Serializable {
    @ManyToOne
    public Endereco endereco;

    @Column(name = "valor")
    private Float valor;
}