package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.Cliente;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Compra;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.config.EntidadeDominio;
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
@Entity(name = "_cupom_promocional")
@SQLDelete(sql = "update _cupom_promocional set habilitado = 0 where id = ?")
public class CupomPromocional extends EntidadeDominio implements Serializable {
    @Column(name = "valor", nullable = false)
    private Float valor;
    @Column(name = "codigo", nullable = false, length = 64)
    private String codigo;
    @Column(name = "quantidade", nullable = false)
    private int quantidade;
    
    @OneToOne
    @JsonIgnore
    private Compra compra;
}
