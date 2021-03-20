package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.dto;


import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.TipoUsuario;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.Usuario;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.validacao.EmailValidator;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.validacao.SenhaForteValidator;
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
public class UsuarioDTO {
    private UUID id;

    @NotBlank(message = "login não pode ser vazio")
    @EmailValidator(message = "Email invalido")
    private String login;
    @NotBlank(message = "senha não pode ser vazio")
    @SenhaForteValidator
    private String senha;

    @NotBlank
    private String nome;

    @NotNull
    private TipoUsuario tipoUsuario;

    public static Usuario dtoToObjeto(UsuarioDTO dto){
        Usuario objeto = DozerBeanMapperBuilder.buildDefault().map(dto, Usuario.class);
        return objeto;
    }

    public static UsuarioDTO objetoToDto(Usuario objeto) {
        return DozerBeanMapperBuilder.buildDefault().map(objeto, UsuarioDTO.class);
    }

}
