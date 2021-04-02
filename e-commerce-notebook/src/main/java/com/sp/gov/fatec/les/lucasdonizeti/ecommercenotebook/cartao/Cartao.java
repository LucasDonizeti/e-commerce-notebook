package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.Cliente;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Pagamento;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.config.EntidadeDominio;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * author LucasDonizeti
 */
@Getter
@Setter
@Entity(name = "_cartao")
@SQLDelete(sql = "update _cartao set habilitado = 0 where id = ?")
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

    @OneToMany
    private List<Pagamento> pagamentoList=new ArrayList<>();
}
