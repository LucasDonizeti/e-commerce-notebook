package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.dto;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao.dto.CartaoDTO;
import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * author LucasDonizeti
 */
@Getter
@Setter
public class PagamentoDTO {
    private UUID id;

    private Boolean habilitado=false;
    @NotNull
    private Float valor;

    @NotNull
    private CartaoDTO cartao;
}
