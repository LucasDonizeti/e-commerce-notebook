package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao.dto;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao.Bandeira;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao.Cartao;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;


/**
 * author LucasDonizeti
 */
@Getter
@Setter
public class CartaoDTO {

    @NotBlank
    @Size(min = 26, max = 21)
    private String numero;

    @NotBlank
    @Min(value = 5)
    private String nome;

    @Size(min = 3, max = 4)
    private String cvv;

    @NotNull
    private Bandeira bandeira;

    public static Cartao dtoToObjeto(CartaoDTO dto){
        Cartao obj = new Cartao();
        if (dto.getNumero()!=null)
            obj.setNumero(dto.getNumero());
        if (dto.getNome()!=null)
            obj.setNome(dto.getNome());
        if (dto.getCvv()!=null)
            obj.setCvv(dto.getCvv());
        if (dto.getBandeira()!=null)
            obj.setBandeira(dto.getBandeira());

        return obj;
    }
}
