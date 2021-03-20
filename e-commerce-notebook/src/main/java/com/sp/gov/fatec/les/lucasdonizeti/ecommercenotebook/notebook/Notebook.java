package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.config.EntidadeDominio;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Produto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

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
@SQLDelete(sql = "update _notebook set habilitado = 0 where id = ?")
public class Notebook extends EntidadeDominio implements Serializable {
    @Column(name = "codigo", length = 30)
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

    public int getRamTotal(){
        int total=0;
        for(RAM ram : ramList){
            total+=ram.getMemoria();
        }
        return total;
    }

    @OneToOne
    @JsonIgnore
    private Produto produto;


    @Column(name = "categoria", nullable = false, length = 15)
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
}
