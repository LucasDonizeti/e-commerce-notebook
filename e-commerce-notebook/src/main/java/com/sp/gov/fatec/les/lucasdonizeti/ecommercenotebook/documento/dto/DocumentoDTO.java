package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento.dto;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento.Documento;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento.TipoDocumento;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento.validacao.DocumentoValidador;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * author LucasDonizeti
 */
@Getter
@Setter
@DocumentoValidador
public class DocumentoDTO {
    @NotBlank
    private String codigo;
    @NotNull
    private TipoDocumento tipoDocumento;

    public static Documento dtoToObjeto(DocumentoDTO dto){
        Documento obj=new Documento();
        if (dto.getCodigo()!=null)
            obj.setCodigo(dto.getCodigo());
        if (dto.getTipoDocumento()!=null)
            obj.setTipoDocumento(dto.getTipoDocumento());

        return obj;
    }
}
