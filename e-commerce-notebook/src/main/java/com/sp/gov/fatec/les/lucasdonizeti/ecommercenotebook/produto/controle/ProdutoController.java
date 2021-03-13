package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.controle;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Produto;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.servico.ProdutoService;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

/**
 * author LucasDonizeti
 */

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ModelAndView inicial(RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView("/produto/index.html");
        mv.addObject("produtos", produtoService.findAll());
        return mv;
    }

    @GetMapping("/teste")
    public ResponseEntity<?> testeinicial() {
        return new ResponseEntity<>(produtoService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/detalhes/{hash}")
    public ModelAndView detalhes(@PathVariable("hash") UUID hash) {
        ModelAndView mv = new ModelAndView("/produto/detalhes.html");
        Produto produto = produtoService.findAll().get(0);

        mv.addObject("produto", produto);
        return mv;
    }
}
