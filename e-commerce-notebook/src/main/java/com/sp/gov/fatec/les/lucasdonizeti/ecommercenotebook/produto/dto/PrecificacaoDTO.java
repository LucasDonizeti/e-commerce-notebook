package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.dto;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Imagem;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Precificacao;
import lombok.Getter;
import lombok.Setter;
import org.dozer.DozerBeanMapperBuilder;

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

    public static Precificacao dtoToObjeto(PrecificacaoDTO dto){
        Precificacao objeto = DozerBeanMapperBuilder.buildDefault().map(dto, Precificacao.class);
        return objeto;
    }

    public static PrecificacaoDTO objetoToDto(Precificacao objeto) {
        return DozerBeanMapperBuilder.buildDefault().map(objeto, PrecificacaoDTO.class);
    }
}
