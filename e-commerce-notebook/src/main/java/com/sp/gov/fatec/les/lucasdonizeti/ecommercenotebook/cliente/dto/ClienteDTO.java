package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.dto;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao.dto.CartaoDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.Genero;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.TipoCliente;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento.dto.DocumentoDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.dto.EnderecoDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.telefone.dto.TelefoneDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.dto.UsuarioDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.SecondaryTable;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

/**
 * author LucasDonizeti
 */
@Getter
@Setter
public class ClienteDTO {
    @Valid
    private UsuarioDTO usuario;

    @NotBlank(message = "nome do cliente não pode ser vazio")
    private String nome;

    @NotNull
    private TipoCliente tipoCliente;

    @NotNull
    private Genero genero;

    @NotNull
    private LocalDate dataNascimento;

    @Valid
    private TelefoneDTO telefone;

    @Valid
    @NotNull(message = "Insira pelo menos 1 documento!")
    private List<CartaoDTO> cartoes;

    @Valid
    @NotNull(message = "Insira pelo menos 1 documento!")
    private List<DocumentoDTO> documentos;

    @Valid
    @NotNull(message = "Insira pelo menos 1 endereço!")
    private List<EnderecoDTO> enderecos;

}
