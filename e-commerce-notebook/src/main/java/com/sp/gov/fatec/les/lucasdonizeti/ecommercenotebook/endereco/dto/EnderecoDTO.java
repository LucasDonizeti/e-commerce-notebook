package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.dto;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.Endereco;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.Longradouro;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.TipoResidencia;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.validacao.CepValidador;
import lombok.Getter;
import lombok.Setter;
import org.dozer.DozerBeanMapper;
import org.dozer.DozerBeanMapperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * author LucasDonizeti
 */
@Getter
@Setter
public class EnderecoDTO {
    private UUID id;

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
    @CepValidador
    private String cep;

    @NotNull
    private Longradouro longradouro;

    @NotNull
    private TipoResidencia tipoResidencia;

    @Valid
    @NotNull
    private CidadeDTO cidade;

    public static Endereco dtoToObjeto(EnderecoDTO dto){
        Endereco endereco = DozerBeanMapperBuilder.buildDefault().map(dto, Endereco.class);
        return endereco;
    }

    public static EnderecoDTO objetoToDto(Endereco e) {
        return DozerBeanMapperBuilder.buildDefault().map(e, EnderecoDTO.class);
    }
}
