package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao.dto;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao.Bandeira;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao.Cartao;
import lombok.Getter;
import lombok.Setter;
import org.dozer.DozerBeanMapperBuilder;

import javax.validation.constraints.*;
import java.util.UUID;


/**
 * author LucasDonizeti
 */
@Getter
@Setter
public class CartaoDTO {
    private UUID id;

    @NotBlank
    @Size(min = 7, max = 21)
    private String numero;

    @NotBlank
    @Size(min = 5, message = "nome muito curto")
    private String nome;

    @NotBlank
    @Size(min = 3, max = 4)
    private String cvv;

    @NotNull
    private Bandeira bandeira;

    public static Cartao dtoToObjeto(CartaoDTO dto){
        Cartao cartao = DozerBeanMapperBuilder.buildDefault().map(dto, Cartao.class);
        return cartao;
    }
    public static CartaoDTO objetoToDto(Cartao cartao) {
        return DozerBeanMapperBuilder.buildDefault().map(cartao, CartaoDTO.class);
    }
}
