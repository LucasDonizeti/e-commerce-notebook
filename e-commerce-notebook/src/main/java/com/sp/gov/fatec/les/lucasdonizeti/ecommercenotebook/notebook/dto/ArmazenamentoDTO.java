package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.dto;


import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.Armazenamento;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.TipoArmazenamento;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Imagem;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.dto.ImagemDTO;
import lombok.Getter;
import lombok.Setter;
import org.dozer.DozerBeanMapperBuilder;

import javax.validation.constraints.NotNull;
import java.util.UUID;


/**
 * author LucasDonizeti
 */
@Getter
@Setter
public class ArmazenamentoDTO {
    private UUID id;
    @NotNull
    private int memoria;

    @NotNull
    private TipoArmazenamento tipoArmazenamento;

    public static Armazenamento dtoToObjeto(ArmazenamentoDTO dto){
        Armazenamento objeto = DozerBeanMapperBuilder.buildDefault().map(dto, Armazenamento.class);
        return objeto;
    }

    public static ArmazenamentoDTO objetoToDto(Armazenamento objeto) {
        return DozerBeanMapperBuilder.buildDefault().map(objeto, ArmazenamentoDTO.class);
    }
}
