package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.administrador;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.config.EntidadeDominio;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.io.Serializable;

/**
 * author LucasDonizeti
 */

@Getter
@Setter
@Entity(name = "_administrador")
public class Administrador extends EntidadeDominio implements Serializable {
    @OneToOne
    private Usuario usuario;
}
