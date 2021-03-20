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
@Entity(name = "_gpu")
@SQLDelete(sql = "update _gpu set habilitado = 0 where id = ?")
public class GPU extends EntidadeDominio implements Serializable {
    @Column(name = "modelo", length = 50)
    private String modelo;

    @OneToOne
    @JsonIgnore
    private Notebook notebook;
}
