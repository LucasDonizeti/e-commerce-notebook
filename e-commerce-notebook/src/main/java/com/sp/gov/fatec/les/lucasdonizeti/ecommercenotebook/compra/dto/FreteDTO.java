package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.dto;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Compra;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Frete;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.dto.EnderecoDTO;
import lombok.Getter;
import lombok.Setter;
import org.dozer.DozerBeanMapperBuilder;

import javax.validation.constraints.NotNull;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * author LucasDonizeti
 */

@Getter
@Setter
public class FreteDTO {
    private UUID id;
    @NotNull
    public EnderecoDTO endereco;

    @NotNull
    private Float valor=0f;

    public static FreteDTO objetoToDto(Frete frete) {
        FreteDTO compraDTO = DozerBeanMapperBuilder.buildDefault().map(frete, FreteDTO.class);
        return compraDTO;
    }

    public static Frete dtoToObjeto(FreteDTO freteDTO) {
        Frete compra = DozerBeanMapperBuilder.buildDefault().map(freteDTO, Frete.class);
        return compra;
    }
}
