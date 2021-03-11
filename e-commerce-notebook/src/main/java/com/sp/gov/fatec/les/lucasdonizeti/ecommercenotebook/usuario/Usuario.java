package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.config.EntidadeDominio;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * author LucasDonizeti
 */

@Getter
@Setter
@Entity(name = "_usuario")
public class Usuario extends EntidadeDominio implements Serializable {

    @Column(name = "login", nullable = false, length = 50)
    private String login;

    @Column(name = "senha", nullable = false, length = 50)
    private String senha;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "tipoUsuario", nullable = false, length = 15)
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

}
