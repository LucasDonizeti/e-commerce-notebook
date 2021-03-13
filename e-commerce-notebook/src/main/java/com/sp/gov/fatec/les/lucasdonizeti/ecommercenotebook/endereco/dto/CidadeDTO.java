package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.dto;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.Cidade;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.Estado;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * author LucasDonizeti
 */
@Getter
@Setter
public class CidadeDTO {
    @NotBlank
    @Min(value = 5)
    private String nome;

    @NotNull
    private Estado estado;

    public static Cidade dtoToObjeto(CidadeDTO dto){
        Cidade obj=new Cidade();
        if (dto.getNome()!=null)
            obj.setNome(dto.getNome());

        if (dto.getEstado()!=null)
            obj.setEstado(dto.getEstado());

        return obj;
    }
}
