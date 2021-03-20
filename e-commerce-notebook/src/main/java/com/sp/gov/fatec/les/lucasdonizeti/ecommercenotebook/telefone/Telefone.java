package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.telefone;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.Genero;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.config.EntidadeDominio;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

/**
 * author LucasDonizeti
 */

@Getter
@Setter
@Entity(name = "_telefone")
@SQLDelete(sql = "update _telefone set habilitado = 0 where id = ?")
public class Telefone extends EntidadeDominio implements Serializable {
    @Column(name = "numero", nullable = false, length = 9)
    private String numero;
    @Column(name = "ddd", nullable = false, length = 3)
    private String ddd;

    @Column(name = "tipo", nullable = false, length = 5)
    @Enumerated(EnumType.STRING)
    private TipoTelefone tipoTelefone;
}
