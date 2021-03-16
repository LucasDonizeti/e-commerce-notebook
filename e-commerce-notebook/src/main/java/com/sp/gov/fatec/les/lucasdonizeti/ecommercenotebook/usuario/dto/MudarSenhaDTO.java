package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.dto;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.TipoUsuario;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.validacao.EmailValidator;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.validacao.SenhaForteValidator;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * author LucasDonizeti
 */
@Getter
@Setter
public class MudarSenhaDTO {

    @NotBlank(message = "senha não pode ser vazio")
    private String antiga;

    @NotBlank(message = "senha não pode ser vazio")
    @SenhaForteValidator
    private String nova;

    @NotBlank(message = "senha não pode ser vazio")
    private String repetida;

}
