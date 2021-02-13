package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente;


import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.pessoa.Pessoa;
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
@Entity
public class Cliente extends Pessoa {
    @ManyToOne
    private TipoCliente tipoCliente;

    @Column(name = "nome", unique = true, nullable = false)
    private String nome;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "senha", unique = true, nullable = false)
    private String senha;
}
