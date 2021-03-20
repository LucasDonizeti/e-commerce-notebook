package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.telefone.dto;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.Armazenamento;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.RAM;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Imagem;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Produto;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.dto.ProdutoDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.telefone.Telefone;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.telefone.TipoTelefone;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.telefone.validacao.DDDTelefoneValidador;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.telefone.validacao.NumeroTelefoneValidador;
import lombok.Getter;
import lombok.Setter;
import org.dozer.DozerBeanMapperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * author LucasDonizeti
 */
@Getter
@Setter
public class TelefoneDTO {
    private UUID id;
    @NotBlank
    @NumeroTelefoneValidador
    private String numero;

    @NotBlank
    @DDDTelefoneValidador
    private String ddd;

    @NotNull
    private TipoTelefone tipoTelefone;

    public static Telefone dtoToObjeto(TelefoneDTO dto){
        Telefone objeto = DozerBeanMapperBuilder.buildDefault().map(dto, Telefone.class);
        return objeto;
    }

    public static TelefoneDTO objetoToDto(Telefone objeto) {
        return DozerBeanMapperBuilder.buildDefault().map(objeto, TelefoneDTO.class);
    }
}
