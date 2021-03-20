package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento.dto;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.Cupom;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.dto.CupomDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento.Documento;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento.TipoDocumento;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento.validacao.DocumentoValidador;
import lombok.Getter;
import lombok.Setter;
import org.dozer.DozerBeanMapper;
import org.dozer.DozerBeanMapperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;


/**
 * author LucasDonizeti
 */
@Getter
@Setter
@DocumentoValidador
public class DocumentoDTO {
    private UUID id;

    @NotBlank
    private String codigo;
    @NotNull
    private TipoDocumento tipoDocumento;

    public static DocumentoDTO objetoToDto(Documento d){
        return DozerBeanMapperBuilder.buildDefault().map(d, DocumentoDTO.class);
    }

    public static Documento dtoToObjeto(DocumentoDTO documentoDTO){
        Documento documento = DozerBeanMapperBuilder.buildDefault().map(documentoDTO, Documento.class);
        return documento;
    }
}
