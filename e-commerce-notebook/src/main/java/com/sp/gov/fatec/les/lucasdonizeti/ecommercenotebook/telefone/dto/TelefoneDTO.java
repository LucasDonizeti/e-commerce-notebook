package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.telefone.dto;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.telefone.Telefone;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.telefone.TipoTelefone;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.telefone.validacao.DDDTelefoneValidador;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.telefone.validacao.NumeroTelefoneValidador;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * author LucasDonizeti
 */
@Getter
@Setter
public class TelefoneDTO {
    @NotBlank
    @NumeroTelefoneValidador
    private String numero;

    @NotBlank
    @DDDTelefoneValidador
    private String ddd;

    @NotNull
    private TipoTelefone tipoTelefone;

    public static Telefone dtoToObjeto(TelefoneDTO dto){
        Telefone obj=new Telefone();
        if (dto.getDdd()!=null)
            obj.setDdd(dto.getDdd());
        if (dto.getNumero()!=null)
            obj.setNumero(dto.getNumero());
        if (dto.getTipoTelefone()!=null)
            obj.setTipoTelefone(dto.getTipoTelefone());

        return obj;
    }

    public static TelefoneDTO ObjetoToDto(Telefone telefone) {
        TelefoneDTO telefoneDTO=new TelefoneDTO();
        telefoneDTO.setTipoTelefone(telefone.getTipoTelefone());
        telefoneDTO.setDdd(telefone.getDdd());
        telefoneDTO.setNumero(telefone.getNumero());
        return telefoneDTO;
    }
}
