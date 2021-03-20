package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto;

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
@Entity(name = "_imagem")
@SQLDelete(sql = "update _imagem set habilitado = 0 where id = ?")
public class Imagem extends EntidadeDominio implements Serializable {
    @ManyToOne
    private Produto produto;

    @Column(name = "link", length = 128)
    private String link;
}
