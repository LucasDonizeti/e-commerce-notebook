package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.dto;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.TipoUsuario;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.Usuario;
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
public class UsuarioDTO {
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

    private static Usuario dtoToObjeto(UsuarioDTO dto){
        Usuario objeto=new Usuario();
        if (dto.getSenha()!=null)
            objeto.setSenha(dto.getSenha());
        if (dto.getNome()!=null)
            objeto.setNome(dto.getNome());
        if (dto.getLogin()!=null)
            objeto.setLogin(dto.getLogin());
        if (dto.getTipoUsuario()!=null)
            objeto.setTipoUsuario(dto.getTipoUsuario());

        return objeto;
    }
}
