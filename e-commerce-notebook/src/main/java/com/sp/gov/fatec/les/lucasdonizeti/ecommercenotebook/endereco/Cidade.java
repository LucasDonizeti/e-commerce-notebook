package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.config.EntidadeDominio;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.TipoUsuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * author LucasDonizeti
 */

@Getter
@Setter
@Entity(name = "_cidade")
public class Cidade extends EntidadeDominio implements Serializable {
    @Column(name = "cidade", length = 50)
    private String nome;
    @Column(name = "estado", nullable = false, length = 15)
    @Enumerated(EnumType.STRING)
    private Estado estado;
}
