package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.dto;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.Cidade;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.Endereco;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.Longradouro;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.TipoResidencia;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * author LucasDonizeti
 */
@Getter
@Setter
public class EnderecoDTO {
    @NotBlank
    @Size(min=5, message = "bairro invalido")
    private String bairro;
    @NotBlank
    @Size(min=1)
    private String numero;
    @NotBlank
    @Size(min=5, message = "rua invalida")
    private String rua;
    @NotBlank
    @Size(min = 8, max = 8, message = "Cep invalido")
    private String cep;

    @NotNull
    private Longradouro longradouro;

    @NotNull
    private TipoResidencia tipoResidencia;

    @Valid
    @NotNull
    private CidadeDTO cidade;

    public static Endereco dtoToObjeto(EnderecoDTO dto){
        Endereco obj=new Endereco();
        if (dto.getNumero()!=null)
            obj.setNumero(dto.getNumero());
        if (dto.getBairro()!=null)
            obj.setBairro(dto.getBairro());
        if (dto.getCep()!=null)
            obj.setCep(dto.getCep());
        if (dto.getLongradouro()!=null)
            obj.setLongradouro(dto.getLongradouro());
        if (dto.getTipoResidencia()!=null)
            obj.setTipoResidencia(dto.getTipoResidencia());
        if (dto.getCidade()!=null)
            obj.setCidade(CidadeDTO.dtoToObjeto(dto.getCidade()));
        if(dto.getRua()!=null)
            obj.setRua(dto.getRua());

        return obj;
    }

    public static EnderecoDTO objetoToDto(Endereco e) {
        EnderecoDTO enderecoDTO=new EnderecoDTO();
        enderecoDTO.setLongradouro(e.getLongradouro());
        enderecoDTO.setBairro(e.getBairro());
        enderecoDTO.setNumero(e.getNumero());
        enderecoDTO.setCep(e.getCep());
        enderecoDTO.setTipoResidencia(e.getTipoResidencia());
        enderecoDTO.setRua(e.getRua());
        enderecoDTO.setCidade(CidadeDTO.objetoToDto(e.getCidade()));
        return enderecoDTO;
    }
}
