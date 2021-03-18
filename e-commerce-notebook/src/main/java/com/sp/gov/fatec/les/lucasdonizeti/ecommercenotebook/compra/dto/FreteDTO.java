package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.dto;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.dto.EnderecoDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * author LucasDonizeti
 */

@Getter
@Setter
public class FreteDTO {
    @NotNull
    public EnderecoDTO endereco;

    @NotNull
    private Float valor=0f;
}
