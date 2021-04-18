package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.dto;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.CupomPromocional;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.CupomTroca;
import lombok.Getter;
import lombok.Setter;
import org.dozer.DozerBeanMapperBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * author LucasDonizeti
 */

@Getter
@Setter
public class CupomPromocionalDTO {
    private UUID id;
    private Boolean habilitado;
    @NotNull
    private Float valor;
    @NotEmpty
    private String codigo;

    public static CupomPromocionalDTO objetoToDto(CupomPromocional cupom){
        return DozerBeanMapperBuilder.buildDefault().map(cupom, CupomPromocionalDTO.class);
    }

    public static CupomPromocional dtoToObjeto(CupomPromocionalDTO cupomDTO){
        return DozerBeanMapperBuilder.buildDefault().map(cupomDTO, CupomPromocional.class);
    }
}
