package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.config.EntidadeDominio;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Produto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * author LucasDonizeti
 */
@Getter
@Setter
@Entity(name = "_notebook")
public class Notebook extends EntidadeDominio implements Serializable {
    @Column(name = "codigo", length = 10)
    private String codigo;

    @Column(name = "marca", length = 50)
    private String marca;

    @Column(name = "modelo", length = 50)
    private String modelo;

    @OneToOne(cascade = CascadeType.ALL)
    private Tela tela;

    @OneToOne(cascade = CascadeType.ALL)
    private CPU cpu;

    @OneToOne(cascade = CascadeType.ALL)
    private GPU gpu;

    @Column(name = "so", nullable = false, length = 15)
    @Enumerated(EnumType.STRING)
    private SO so;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    List<RAM> ramList = new ArrayList();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    List<Armazenamento> armazenamentoList = new ArrayList();

    @OneToOne(cascade = CascadeType.ALL)
    private Produto produto;


    @Column(name = "categoria", nullable = false, length = 15)
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
}