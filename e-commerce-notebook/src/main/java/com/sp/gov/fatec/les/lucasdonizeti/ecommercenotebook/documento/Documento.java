package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.Cliente;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.config.EntidadeDominio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * author LucasDonizeti
 */

@Getter
@Setter
@Entity(name = "_documento")
public class Documento extends EntidadeDominio implements Serializable {
    @Column(name = "codigo", unique = true, nullable = false)
    private String codigo;

    @Column(name = "tipo_documento", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;

    @OneToOne
    @JoinColumn(name = "cliente_id")
    @JsonIgnore
    private Cliente cliente;
}
