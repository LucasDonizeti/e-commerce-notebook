package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.dto;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.TipoEndereco;
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
public class TipoEnderecoDTO {
    private String nome;
    private String descricao;

    public static TipoEnderecoDTO objectToDTO(TipoEndereco tipoEndereco) {
        TipoEnderecoDTO tipoEnderecoDTO=new TipoEnderecoDTO();

        if (tipoEndereco.getNome() != null)
            tipoEnderecoDTO.setNome(tipoEndereco.getNome());

        if (tipoEndereco.getDescricao() != null)
            tipoEnderecoDTO.setDescricao(tipoEndereco.getDescricao());

        return tipoEnderecoDTO;
    }
}
