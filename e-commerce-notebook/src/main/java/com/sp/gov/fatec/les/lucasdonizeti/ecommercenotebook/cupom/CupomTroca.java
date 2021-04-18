package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.Cliente;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Compra;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.config.EntidadeDominio;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * author LucasDonizeti
 */

@Getter
@Setter
@Entity(name = "_cupom_troca")
@SQLDelete(sql = "update _cupom_troca set habilitado = 0 where id = ?")
public class CupomTroca extends EntidadeDominio implements Serializable {
    @Column(name = "valor", nullable = false)
    private Float valor;
    @Column(name = "codigo", nullable = false, length = 10)
    private String codigo;

    @ManyToOne
    @JsonIgnore
    private Cliente cliente;

    @ManyToOne
    @JsonIgnore
    private Compra compra;
}
