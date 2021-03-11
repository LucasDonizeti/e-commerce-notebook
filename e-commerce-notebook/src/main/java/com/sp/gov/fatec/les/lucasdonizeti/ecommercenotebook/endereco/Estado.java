package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.config.EntidadeDominio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * author LucasDonizeti
 */

/*
@Getter
@Setter
@Entity(name = "_estado")
public class Estado extends EntidadeDominio implements Serializable {
    @Column(name = "cidade", length = 50)
    private String nome;
}

 */

public enum Estado {
    ACRE("AC"),ALAGOAS("AL"),AMAPA("AP"), AMAZONAS("AM"),
    BAHIA("BH"), CEARA("CE"), DISTRITO_FEDERAL("DP"), ESPIRITO_SANTO("ES"),
    GOIAS("GO"), MARANHAO("MA"), MATO_GROSSO("MT"), MATO_GROSSO_DO_SUL("MS"),
    MINAS_GERAIS("MG"), PARA("PA"), PARAIBA("PB"), PARANA("PR"),
    PERNAMBUCO("PE"), PIAIU("PI"), RIO_DE_JANEIRO("RJ"), RIO_GRANDE_DO_NORTE("RN"),
    RIO_GRANDE_DO_SUL("RS"), RONDONIA("RO"), RORAIMA("RR"), SANTA_CATARINA("SC"),
    SAO_PAULO("SP"), SERGIPE("SE"), TOCANTINS("TO");


    private String descricao;

    Estado(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao() {
        return descricao;
    }
}
