package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.Cliente;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.config.EntidadeDominio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * author LucasDonizeti
 */
@Getter
@Setter
@Entity(name = "_cupom")
public class Cupom extends EntidadeDominio implements Serializable {
    @Column(name = "valor", nullable = false)
    private Long valor;

    @Column(name = "tipo", nullable = false, length = 15)
    @Enumerated(EnumType.STRING)
    private TipoCupom tipoCupom;

    @ManyToOne
    @JsonIgnore
    private Cliente cliente;
}
