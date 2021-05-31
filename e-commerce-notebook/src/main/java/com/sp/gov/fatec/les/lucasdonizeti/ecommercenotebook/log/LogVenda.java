package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.log;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.config.EntidadeDominio;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Produto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * author LucasDonizeti
 */

@Getter
@Setter
@Entity(name = "_log_venda")
public class LogVenda  extends EntidadeDominio implements Serializable {
    @OneToOne
    private Produto produto;
    @Column(name = "quantidade", nullable = false, updatable = false)
    private int quantidade;
    @Column(name = "data", nullable = false, updatable = false)
    private LocalDate data;
    @Column(name="acao", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private LogVendaAcao acao;
}
