package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.Cliente;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.config.EntidadeDominio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * author LucasDonizeti
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "_endereco")
@Entity
public class Endereco extends EntidadeDominio {
    @ManyToOne
    private Cliente cliente;
    @Column(name = "longradouro")
    private String longradouro;
    @Column(name = "codigo", nullable = false)
    private String numero;
    @Column(name = "cep", nullable = false)
    private String cep;
    @Column(name = "complemento", nullable = false)
    private String complemento;
    @ManyToOne
    private Cidade cidade;
    @ManyToOne
    private TipoEndereco tipoEndereco;
}
