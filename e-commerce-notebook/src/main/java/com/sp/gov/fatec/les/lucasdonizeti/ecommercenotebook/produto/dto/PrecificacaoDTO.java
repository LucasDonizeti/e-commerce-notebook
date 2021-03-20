package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * author LucasDonizeti
 */
@Getter
@Setter
public class PrecificacaoDTO {
    private UUID id;
    @NotBlank
    private String nome;
    @NotNull
    private Float margemDeLucro;
}
