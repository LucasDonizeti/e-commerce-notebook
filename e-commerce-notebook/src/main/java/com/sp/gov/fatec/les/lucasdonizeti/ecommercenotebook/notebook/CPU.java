package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.config.EntidadeDominio;
import lombok.*;
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
@Entity(name = "_cpu")
@SQLDelete(sql = "update _cpu set habilitado = 0 where id = ?")
public class CPU extends EntidadeDominio implements Serializable {
    @Column(name = "modelo", length = 50)
    private String modelo;

    @OneToOne
    @JsonIgnore
    private Notebook notebook;
}
