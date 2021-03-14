package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco;

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
@Entity(name = "_endereco")
public class Endereco extends EntidadeDominio implements Serializable {
    @ManyToOne(optional = true)
    @JsonIgnore
    private Cliente cliente;

    @Column(name = "rua", length = 50)
    private String rua;
    @Column(name = "bairro", length = 50)
    private String bairro;
    @Column(name = "codigo", nullable = false, length = 5)
    private String numero;
    @Column(name = "cep", nullable = false, length = 8)
    private String cep;

    @Column(name = "longradouro", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Longradouro longradouro;

    @Column(name = "tipo_residencia", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private TipoResidencia tipoResidencia;

    @OneToOne(cascade = CascadeType.ALL)
    private Cidade cidade;
}
