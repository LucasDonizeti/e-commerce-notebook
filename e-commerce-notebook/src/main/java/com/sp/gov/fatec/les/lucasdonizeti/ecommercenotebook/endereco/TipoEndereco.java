package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.entidade.EntidadeDominio;
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
@Table(name = "_tipo_endereco")
@Entity
public class TipoEndereco extends EntidadeDominio {
    @Column(name = "descricao", nullable = false, unique = true)
    private String descricao;
    @Column(name = "nome", nullable = false, unique = true)
    private String nome;
}
