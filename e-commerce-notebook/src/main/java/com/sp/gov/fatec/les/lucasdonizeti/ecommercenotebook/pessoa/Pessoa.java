package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.pessoa;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento.Documento;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.entidade.EntidadeDominio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * author LucasDonizeti
 */

@Getter
@Setter
@Table(name = "_pessoa")
@Entity
public class Pessoa extends EntidadeDominio {
    @OneToMany(mappedBy = "pessoa")
    @JsonManagedReference
    private List<Documento> documentos = new ArrayList<>();
}
