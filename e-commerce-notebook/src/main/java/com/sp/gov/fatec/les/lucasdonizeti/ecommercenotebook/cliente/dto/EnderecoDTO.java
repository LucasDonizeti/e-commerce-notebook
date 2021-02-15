package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.dto;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.Endereco;
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
public class EnderecoDTO {
    private String numero;
    private String cep;
    private String longradouro;
    private String complemento;
    private String cidade;
    private String estado;
    private TipoEnderecoDTO tipoEndereco;

    public static EnderecoDTO objectToDTO(Endereco endereco) {
        EnderecoDTO enderecoDTO=new EnderecoDTO();
        if (endereco.getNumero() != null)
            enderecoDTO.setNumero(endereco.getNumero());
        if (endereco.getCep() != null)
            enderecoDTO.setCep(endereco.getCep());
        if (endereco.getLongradouro() != null)
            enderecoDTO.setLongradouro(endereco.getLongradouro());
        if (endereco.getComplemento() != null)
            enderecoDTO.setComplemento(endereco.getComplemento());
        if (endereco.getCidade() != null)
            enderecoDTO.setCidade(endereco.getCidade().getDescricao());
        if (endereco.getCidade().getEstado() != null)
            enderecoDTO.setEstado(endereco.getCidade().getEstado().getDescricao());
        if (endereco.getTipoEndereco() != null)
            enderecoDTO.setTipoEndereco(TipoEnderecoDTO.objectToDTO(endereco.getTipoEndereco()));

        return enderecoDTO;
    }
}
