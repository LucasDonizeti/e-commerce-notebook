package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao.Cartao;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Compra;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.config.EntidadeDominio;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.CupomTroca;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento.Documento;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.Endereco;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.telefone.Telefone;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.Usuario;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * author LucasDonizeti
 */

@Getter
@Setter
@Entity(name = "_cliente")
@SQLDelete(sql = "update _cliente set habilitado = 0 where id = ?")
public class Cliente extends EntidadeDominio implements Serializable {
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "rank", nullable = false)
    private int rank=0;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Documento> documentos;

    @Column(name = "tipo_cliente", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private TipoCliente tipoCliente;

    @Column(name = "genero", nullable = false, length = 9)
    @Enumerated(EnumType.STRING)
    private Genero genero;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cartao> cartoes=new ArrayList<>();

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    private Telefone telefone;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos=new ArrayList<>();

    @OneToMany
    private List<CupomTroca> cupomsDeTroca=new ArrayList<>();

    @OneToOne
    private Usuario usuario;

    @OneToMany
    private List<Compra> compras=new ArrayList<>();
}
