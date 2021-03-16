package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.dto;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.TipoUsuario;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.Usuario;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.validacao.EmailValidator;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.validacao.SenhaForteValidator;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * author LucasDonizeti
 */
@Getter
@Setter
public class UsuarioDTO {
    private UUID hash;

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

    public static UsuarioDTO objetoToDto(Usuario usuario){
        UsuarioDTO usuarioDTO=new UsuarioDTO();
        usuarioDTO.setHash(usuario.getHash());
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setTipoUsuario(usuario.getTipoUsuario());
        usuarioDTO.setLogin(usuario.getLogin());
        usuarioDTO.setSenha(usuario.getSenha());

        return usuarioDTO;
    }

}
