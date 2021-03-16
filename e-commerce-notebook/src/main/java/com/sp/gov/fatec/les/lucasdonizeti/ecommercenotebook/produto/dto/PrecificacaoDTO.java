package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * author LucasDonizeti
 */
@Getter
@Setter
public class PrecificacaoDTO {
    @NotBlank
    private String nome;
    @NotNull
    private Float margemDeLucro;
}
