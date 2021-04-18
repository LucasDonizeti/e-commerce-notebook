package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.dto;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao.Cartao;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao.dto.CartaoDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.Cliente;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.Genero;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.TipoCliente;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.dto.CupomTrocaDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento.Documento;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento.dto.DocumentoDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.Endereco;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.dto.EnderecoDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.telefone.dto.TelefoneDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.dto.UsuarioDTO;
import lombok.Getter;
import lombok.Setter;
import org.dozer.DozerBeanMapperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * author LucasDonizeti
 */
@Getter
@Setter
public class ClienteDTO {
    private UUID id;

    @Valid
    private UsuarioDTO usuario;

    @NotNull
    private TipoCliente tipoCliente;

    @NotNull
    private Genero genero;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimentoDto;

    @Valid
    private TelefoneDTO telefone;

    private int rank;

    @Valid
    @NotNull(message = "Insira pelo menos 1 documento!")
    @Size(min = 1, message = "insira pelo menos 1 cartao")
    private List<CartaoDTO> cartoes = new ArrayList<>();

    @Valid
    @NotNull(message = "Insira pelo menos 1 documento!")
    @Size(min = 1, message = "insira pelo menos 1 documento")
    private List<DocumentoDTO> documentos = new ArrayList<>();

    @Valid
    @NotNull(message = "Insira pelo menos 1 endereço!")
    @Size(min = 1, message = "insira pelo menos 1 endereço")
    private List<EnderecoDTO> enderecos = new ArrayList<>();

    private List<CupomTrocaDTO> cupomsTroca=new ArrayList<>();

    public void addEmptyDocumento() {
        DocumentoDTO documentoDTO = new DocumentoDTO();
        this.documentos.add(documentoDTO);
    }

    public void rmDocumento(int indice) {
        this.documentos.remove(indice);
    }

    public void addEmptyCartao() {
        CartaoDTO cartaoDTO = new CartaoDTO();
        this.cartoes.add(cartaoDTO);
    }

    public void rmCartao(int indice) {
        this.cartoes.remove(indice);
    }

    public void addEmptyEndereco() {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        this.enderecos.add(enderecoDTO);
    }

    public void rmEndereco(int i) {
        this.enderecos.remove(i);
    }

    public static ClienteDTO objetoToDto(Cliente cliente) {
        ClienteDTO clienteDTO = DozerBeanMapperBuilder.buildDefault().map(cliente, ClienteDTO.class);
        clienteDTO.setDataNascimentoDto(cliente.getDataNascimento());
        return clienteDTO;
    }

    public static Cliente dtoToObjeto(ClienteDTO clienteDTO) {
        Cliente cliente = DozerBeanMapperBuilder.buildDefault().map(clienteDTO, Cliente.class);
        cliente.setDataNascimento(clienteDTO.getDataNascimentoDto());
        return cliente;
    }
}
