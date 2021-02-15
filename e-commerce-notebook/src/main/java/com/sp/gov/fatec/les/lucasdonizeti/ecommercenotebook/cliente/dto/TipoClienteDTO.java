package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.dto;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.TipoCliente;
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
public class TipoClienteDTO {
    private String nome;
    private String descricao;

    public static TipoClienteDTO objetoToDTO(TipoCliente tipoCliente) {
        TipoClienteDTO tipoClienteDTO=new TipoClienteDTO();
        if (tipoCliente.getNome() != null)
            tipoClienteDTO.setNome(tipoCliente.getNome());
        if (tipoCliente.getDescricao() != null)
            tipoClienteDTO.setDescricao(tipoCliente.getDescricao());

        return tipoClienteDTO;
    }
}
