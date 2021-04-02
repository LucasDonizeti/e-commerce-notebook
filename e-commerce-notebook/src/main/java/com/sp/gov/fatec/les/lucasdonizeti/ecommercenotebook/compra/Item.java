package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.config.EntidadeDominio;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Produto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.io.Serializable;


/**
 * author LucasDonizeti
 */

@Getter
@Setter
@Entity(name = "_item")
@SQLDelete(sql = "update _item set habilitado = 0 where id = ?")
public class Item extends EntidadeDominio implements Serializable {
    @ManyToOne
    private Produto produto;

    @Column(name = "quantidade")
    private int quantidade=1;

    @Column(name = "status", length = 30)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JsonIgnore
    private Compra compra;
}
