package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.controle;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.servico.ClienteServico;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.servico.ProdutoService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * author LucasDonizeti
 */

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioServico usuarioServico;
    private final ProdutoService produtoService;
    private final ClienteServico clienteServico;

    @Autowired
    public UsuarioController(UsuarioServico usuarioServico, ProdutoService produtoService, ClienteServico clienteServico) {
        this.usuarioServico = usuarioServico;
        this.produtoService = produtoService;
        this.clienteServico = clienteServico;
    }

    @GetMapping("/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv=new ModelAndView("/usuario/login.html");
        mv.addObject("usuarioDto", new UsuarioDTO());
        return mv;
    }

    @PostMapping("/login")
    public ModelAndView logar(@ModelAttribute("usuarioDto") UsuarioDTO usuarioDTO,
                              BindingResult errors,HttpServletRequest request, HttpServletResponse response) {
        if (errors.hasErrors()){
            ModelAndView mv = new ModelAndView("/usuario/login.html");
            mv.addObject("usuarioDTO", usuarioDTO);
            mv.addObject("erros", errors.getAllErrors());
            return mv;
        }

        if (usuarioDTO.getLogin().equals("teste@gmail.com") && usuarioDTO.getSenha().equals("1234aaBB$")) {
            ModelAndView mv = new ModelAndView("redirect:/cliente/carrinho/" + clienteServico.findAll().get(0).getId());

            Usuario usuario=new Usuario();
            usuario.setNome("usuario teste");
            usuario.setTipoUsuario(TipoUsuario.CLIENTE);
            usuario.setLogin(usuarioDTO.getLogin());
            usuario.setSenha(usuarioDTO.getSenha());
            mv.addObject("usuarioLogado", usuario);
            mv.addObject("produtos", produtoService.findAll());
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
