package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.dto;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento.Documento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * author LucasDonizeti
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DocumentoDTO {
    private String codigo;
    private String validade;
    private TipoDocumentoDTO tipoDocumentoDTO;

    public static DocumentoDTO objectToDTO(Documento documento) {
        DocumentoDTO documentoDTO = new DocumentoDTO();
        if (documento.getCodigo() != null)
            documentoDTO.setCodigo(documento.getCodigo());
        if (documento.getValidade() != null)
            documentoDTO.setValidade(new SimpleDateFormat("dd/MM/yyyy").format(documento.getValidade().getTime()));
        if (documento.getTipoDocumento() != null)
            documentoDTO.setTipoDocumentoDTO(TipoDocumentoDTO.objectToDTO(documento.getTipoDocumento()));

        return documentoDTO;
    }
}
