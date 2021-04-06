package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.config.EntidadeDominio;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.TipoUsuario;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * author LucasDonizeti
 */

@Getter
@Setter
@Entity(name = "_cidade")
@SQLDelete(sql = "update _cidade set habilitado = 0 where id = ?")
public class Cidade extends EntidadeDominio implements Serializable {
    @Column(name = "cidade", length = 50)
    private String nome;
    @Column(name = "estado", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private Estado estado;
}
