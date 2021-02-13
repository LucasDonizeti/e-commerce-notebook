package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.entidade.EntidadeDominio;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.pessoa.Pessoa;
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
@Table(name = "_tipo_documento")
@Entity
public class TipoDocumento extends EntidadeDominio {

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "nome", unique = true, nullable = false)
    private String nome;
}
