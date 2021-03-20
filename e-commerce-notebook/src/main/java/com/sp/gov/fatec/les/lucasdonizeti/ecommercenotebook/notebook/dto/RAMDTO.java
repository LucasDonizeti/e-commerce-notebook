package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.dto;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.RAM;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Produto;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.dto.ProdutoDTO;
import lombok.Getter;
import lombok.Setter;
import org.dozer.DozerBeanMapperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * author LucasDonizeti
 */
@Getter
@Setter
public class RAMDTO {
    private UUID id;
    @NotNull
    private int memoria;
    @NotBlank
    @Size(max = 8)
    private String clock;

    public static RAM dtoToObjeto(RAMDTO dto){
        RAM objeto = DozerBeanMapperBuilder.buildDefault().map(dto, RAM.class);
        return objeto;
    }

    public static RAMDTO objetoToDto(RAM objeto) {
        return DozerBeanMapperBuilder.buildDefault().map(objeto, RAMDTO.class);
    }
}
