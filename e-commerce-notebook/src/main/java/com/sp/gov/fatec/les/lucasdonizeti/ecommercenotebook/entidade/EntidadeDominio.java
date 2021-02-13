package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.entidade;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;

/**
 * author LucasDonizeti
 */
@Access(AccessType.FIELD)
@AllArgsConstructor
@Getter
@MappedSuperclass
@Setter
public abstract class EntidadeDominio {
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    protected Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_criacao", nullable = false, updatable = false)
    protected Calendar dataCriacao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_atualizacao", nullable = false, updatable = false)
    protected Calendar dataAtualizacao;

    @Basic
    @Column(name = "habilitado", nullable = false)
    protected boolean habilitado;

    public EntidadeDominio() {
        this.dataCriacao = Calendar.getInstance();
        this.dataAtualizacao = Calendar.getInstance();
        this.habilitado = true;
    }
}
