package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.config;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * author LucasDonizeti
 */

@Access(AccessType.FIELD)
@Getter
@MappedSuperclass
@Setter
public class EntidadeDominio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="uuid-char")
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;


    @Column(name = "data_criacao", nullable = false, updatable = false)
    protected LocalDateTime dataCriacao;

    @Basic
    @Column(name = "habilitado", nullable = false)
    protected boolean habilitado = true;

    public EntidadeDominio() {
        this.id=UUID.randomUUID();
        this.dataCriacao = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntidadeDominio that = (EntidadeDominio) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
