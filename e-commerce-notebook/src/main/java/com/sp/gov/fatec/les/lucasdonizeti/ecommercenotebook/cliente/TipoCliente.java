package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.config.EntidadeDominio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * author LucasDonizeti
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "_tipo_cliente")
@Entity
public class TipoCliente extends EntidadeDominio {
    @Column(name = "nome", unique = true, nullable = false)
    private String nome;
    @Column(name = "descricao", nullable = false)
    private String descricao;
}
