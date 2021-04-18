package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.dto;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.CupomTroca;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.dto.CupomTrocaDTO;
import lombok.Getter;
import lombok.Setter;
import org.dozer.DozerBeanMapperBuilder;

/**
 * author LucasDonizeti
 */
@Getter
@Setter
public class CupomTrocaSelecionadoDTO extends CupomTrocaDTO {
    private Boolean isSelecionado = false;

    public static CupomTrocaSelecionadoDTO objetoToDto(CupomTroca cupom){
        return DozerBeanMapperBuilder.buildDefault().map(cupom, CupomTrocaSelecionadoDTO.class);
    }

    public static CupomTroca dtoToObjeto(CupomTrocaSelecionadoDTO cupomDTO){
        return DozerBeanMapperBuilder.buildDefault().map(cupomDTO, CupomTroca.class);
    }
}
