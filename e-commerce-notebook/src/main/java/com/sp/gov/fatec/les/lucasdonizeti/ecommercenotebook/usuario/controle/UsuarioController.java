package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.controle;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.TipoUsuario;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.Usuario;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.dto.UsuarioDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.servico.UsuarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * author LucasDonizeti
 */

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioServico usuarioServico;

    @Autowired
    public UsuarioController(UsuarioServico usuarioServico) {
        this.usuarioServico = usuarioServico;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mv=new ModelAndView("/usuario/login.html");
        mv.addObject("usuarioDto", new UsuarioDTO());
        return mv;
    }

    @PostMapping("/login")
    public ModelAndView logar(@ModelAttribute("usuarioDto") UsuarioDTO usuarioDTO,
                              BindingResult errors) {
        if (errors.hasErrors()){
            ModelAndView mv = new ModelAndView("/usuario/login.html");
            mv.addObject("usuarioDTO", usuarioDTO);
            mv.addObject("erros", errors.getAllErrors());
            return mv;
        }

        if (usuarioDTO.getLogin().equals("teste@gmail.com") && usuarioDTO.getSenha().equals("1234aaBB$")) {
            ModelAndView mv = new ModelAndView("redirect:/produto");

            Usuario usuario=new Usuario();
            usuario.setNome("usuario teste");
            usuario.setTipoUsuario(TipoUsuario.CLIENTE);
            usuario.setLogin(usuarioDTO.getLogin());
            usuario.setSenha(usuarioDTO.getSenha());
            mv.addObject("usuarioLogado", usuario);
            return mv;
        } else {
            ModelAndView mv = new ModelAndView("/usuario/login.html");
            mv.addObject("usuarioDTO", usuarioDTO);
            mv.addObject("exception", "Login ou senha errada!");
            return mv;
        }

    }

    @GetMapping("/teste")
    public ResponseEntity<?> teste() {
        Usuario usuario = new Usuario();
        usuario.setTipoUsuario(TipoUsuario.CLIENTE);
        usuario.setLogin("login");
        usuario.setSenha("senha");
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
}
