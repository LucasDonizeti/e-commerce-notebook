package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.dto;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.Cliente;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.Cupom;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.TipoCupom;
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
public class CupomDTO {
    private UUID id;
    private Boolean habilitado;
    @NotNull
    private Float valor;
    @NotEmpty
    private String codigo;

    @NotNull
    private TipoCupom tipoCupom;


    public static CupomDTO objetoToDto(Cupom cupom){
        return DozerBeanMapperBuilder.buildDefault().map(cupom, CupomDTO.class);
    }

    public static Cupom dtoToObjeto(CupomDTO cupomDTO){
        Cupom cupom = DozerBeanMapperBuilder.buildDefault().map(cupomDTO, Cupom.class);
        return cupom;
    }
}
