package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.dto;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento.TipoDocumento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * author LucasDonizeti
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TipoDocumentoDTO {
    private String nome;
    private String descricao;

    public static TipoDocumentoDTO objectToDTO(TipoDocumento tipoDocumento) {
        TipoDocumentoDTO tipoDocumentoDTO = new TipoDocumentoDTO();
        if (tipoDocumento.getNome() != null)
            tipoDocumentoDTO.setNome(tipoDocumento.getNome());

        if (tipoDocumento.getDescricao() != null)
            tipoDocumentoDTO.setDescricao(tipoDocumento.getDescricao());

        return tipoDocumentoDTO;
    }
}
