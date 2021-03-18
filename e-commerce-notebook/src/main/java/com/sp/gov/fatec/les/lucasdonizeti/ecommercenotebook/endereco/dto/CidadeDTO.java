package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.dto;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.Estado;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * author LucasDonizeti
 */
@Getter
@Setter
public class CidadeDTO {
    @NotBlank
    @Size(min = 5, message = "cidade invalida")
    private String nome;

    @NotNull
    private Estado estado;

}
