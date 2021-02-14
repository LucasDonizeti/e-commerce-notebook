package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.config.EntidadeDominio;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.pessoa.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;

/**
 * author LucasDonizeti
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "_documento")
@Entity
public class Documento extends EntidadeDominio {
    @Column(name = "codigo", unique = true, nullable = false)
    private String codigo;

    @Temporal(TemporalType.DATE)
    @Column(name = "validade", nullable = false)
    private Calendar validade;

    @ManyToOne(optional = false)
    private TipoDocumento tipoDocumento;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "pessoa_id")
    @JsonBackReference
    private Pessoa pessoa;

}
