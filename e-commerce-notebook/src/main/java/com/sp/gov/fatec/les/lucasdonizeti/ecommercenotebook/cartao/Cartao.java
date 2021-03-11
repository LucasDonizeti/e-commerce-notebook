package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.Cliente;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.config.EntidadeDominio;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.Estado;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * author LucasDonizeti
 */
@Getter
@Setter
@Entity(name = "_cartao")
public class Cartao extends EntidadeDominio implements Serializable {
    @Column(name = "numero", length = 20, nullable = false)
    private String numero;
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;
    @Column(name = "cvv", length = 4, nullable = false)
    private String cvv;

    @Column(name = "bandeira", nullable = false, length = 15)
    @Enumerated(EnumType.STRING)
    private Bandeira bandeira;

    @ManyToOne(optional = true)
    @JsonIgnore
    private Cliente cliente;
}
