package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * author LucasDonizeti
 */

@Access(AccessType.FIELD)
@Getter
@MappedSuperclass
@Setter
public class EntidadeDominio implements Serializable {
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    protected Long id;

    @Basic
    @Column(name = "hash", nullable = false, unique = true, updatable = false)
    @Convert(converter = ConverterUUIDString.class)
    protected UUID hash;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    protected LocalDateTime dataCriacao;

    @Basic
    @Column(name = "habilitado", nullable = false)
    protected boolean habilitado = true;

    public EntidadeDominio() {
        this.hash = UUID.randomUUID();
        this.dataCriacao = LocalDateTime.now();
    }

    public void genHash(){
        this.hash = UUID.randomUUID();
    }
}
