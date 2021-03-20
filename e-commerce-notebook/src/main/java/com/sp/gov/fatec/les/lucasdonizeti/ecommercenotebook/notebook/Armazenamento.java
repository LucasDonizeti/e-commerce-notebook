package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Entity(name = "_armazenamento")
@SQLDelete(sql = "update _armazenamento set habilitado = 0 where id = ?")
public class Armazenamento extends EntidadeDominio implements Serializable {
    @Column(name = "memoria")
    private int memoria;

    @Column(name = "tipo_armazenamento", nullable = false, length = 15)
    @Enumerated(EnumType.STRING)
    private TipoArmazenamento tipoArmazenamento;

    @ManyToOne
    @JsonIgnore
    private Notebook notebook;
}
