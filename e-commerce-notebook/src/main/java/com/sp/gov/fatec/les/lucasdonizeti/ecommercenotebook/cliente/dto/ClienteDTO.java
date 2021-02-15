package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.dto;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * author LucasDonizeti
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClienteDTO {
    private String nome;
    private String email;
    private String senha;
    private TipoClienteDTO tipoCliente;
    private List<DocumentoDTO> documentos = new ArrayList<>();
    private List<EnderecoDTO> enderecos = new ArrayList<>();

    public static ClienteDTO objetoToDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();

        if (cliente.getNome() != null)
            clienteDTO.setNome(cliente.getNome());
        if (cliente.getSenha() != null)
            clienteDTO.setSenha(cliente.getSenha());
        if (cliente.getEmail() != null)
            clienteDTO.setEmail(cliente.getEmail());
        if (cliente.getTipoCliente() != null)
            clienteDTO.setTipoCliente(TipoClienteDTO.objetoToDTO(cliente.getTipoCliente()));

        List<DocumentoDTO> documentoDTOList = new ArrayList<>();
        cliente.getDocumentos().forEach(d -> {
            documentoDTOList.add(DocumentoDTO.objectToDTO(d));
        });

        clienteDTO.setDocumentos(documentoDTOList);

        List<EnderecoDTO> enderecoDTOList = new ArrayList<>();
        cliente.getEnderecoList().forEach(e -> {
            enderecoDTOList.add(EnderecoDTO.objectToDTO(e));
        });

        clienteDTO.setEnderecos(enderecoDTOList);

        return clienteDTO;
    }
}
