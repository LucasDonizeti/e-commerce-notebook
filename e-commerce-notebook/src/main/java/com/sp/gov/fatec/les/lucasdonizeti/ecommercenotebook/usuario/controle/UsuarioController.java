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
        return mv;
    }

    @GetMapping("/login-erro")
    public ModelAndView loginErro(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv=new ModelAndView("/usuario/login.html");
        mv.addObject("erro", "Email ou senha incorretos!");
        return mv;
    }

    @GetMapping("/acesso-negado")
    public ModelAndView acessoNegado() {
        ModelAndView mv=new ModelAndView("/acesso-negado.html");
        return mv;
    }
}
