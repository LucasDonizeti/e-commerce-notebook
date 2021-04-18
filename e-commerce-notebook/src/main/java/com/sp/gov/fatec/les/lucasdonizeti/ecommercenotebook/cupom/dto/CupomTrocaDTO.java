package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.dto;

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
public class CupomTrocaDTO {
    private UUID id;
    private Boolean habilitado;
    @NotNull
    private Float valor;
    @NotEmpty
    private String codigo;

    public static CupomTrocaDTO objetoToDto(CupomTroca cupom){
        return DozerBeanMapperBuilder.buildDefault().map(cupom, CupomTrocaDTO.class);
    }

    public static CupomTroca dtoToObjeto(CupomTrocaDTO cupomDTO){
        return DozerBeanMapperBuilder.buildDefault().map(cupomDTO, CupomTroca.class);
    }
}
