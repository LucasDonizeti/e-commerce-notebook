package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.config.EntidadeDominio;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Not;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * author LucasDonizeti
 */

@Getter
@Setter
@Entity(name = "_ram")
public class RAM extends EntidadeDominio implements Serializable {
    @Column(name = "memoria")
    private int memoria;
    @Column(name = "clock", length = 8)
    private String clock;

    @ManyToOne
    @JsonIgnore
    private Notebook notebook;
}
