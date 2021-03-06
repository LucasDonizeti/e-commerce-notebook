package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.config.EntidadeDominio;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

/**
 * author LucasDonizeti
 */

@Getter
@Setter
@Entity(name = "_precificacao")
@SQLDelete(sql = "update _precificacao set habilitado = 0 where id = ?")
public class Precificacao extends EntidadeDominio implements Serializable {
    @Column(name = "nome", length = 25)
    private String nome;

    @Column(name = "margem_de_lucro")
    private Float margemDeLucro;

    @OneToMany
    private List<Produto> produtoList;
}
