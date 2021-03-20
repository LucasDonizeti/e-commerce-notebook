package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.dto;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Imagem;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Produto;
import lombok.Getter;
import lombok.Setter;
import org.dozer.DozerBeanMapperBuilder;

import java.util.UUID;

/**
 * author LucasDonizeti
 */
@Getter
@Setter
public class ImagemDTO {
    private UUID id;
    private String link;

    public static Imagem dtoToObjeto(ImagemDTO dto){
        Imagem objeto = DozerBeanMapperBuilder.buildDefault().map(dto, Imagem.class);
        return objeto;
    }

    public static ImagemDTO objetoToDto(Imagem objeto) {
        return DozerBeanMapperBuilder.buildDefault().map(objeto, ImagemDTO.class);
    }
}
