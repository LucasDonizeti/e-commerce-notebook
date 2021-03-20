package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.config.EntidadeDominio;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.io.Serializable;

/**
 * author LucasDonizeti
 */
@Getter
@Setter
@Entity(name = "_tela")
@SQLDelete(sql = "update _tela set habilitado = 0 where id = ?")
public class Tela extends EntidadeDominio implements Serializable {
    @Column(name = "tamanho", length = 4)
    private String tamanho;
    @Column(name = "clock", length = 3)
    private String clock;

    @OneToOne
    @JsonIgnore
    private Notebook notebook;
}
