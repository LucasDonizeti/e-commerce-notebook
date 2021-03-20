package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.controle;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Status;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.dto.CompraDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.dto.*;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.service.ArmazenamentoService;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.service.RAMService;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Precificacao;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Produto;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.dto.ImagemDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.dto.PrecificacaoDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.dto.ProdutoDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.servico.ImagemService;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.servico.PrecificacaoService;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.servico.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * author LucasDonizeti
 */

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    private final ProdutoService produtoService;
    private final ImagemService imagemService;
    private final ArmazenamentoService armazenamentoService;
    private final RAMService ramService;
    private final PrecificacaoService precificacaoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService, ImagemService imagemService,
                             ArmazenamentoService armazenamentoService, RAMService ramService, PrecificacaoService precificacaoService) {
        this.produtoService = produtoService;
        this.imagemService = imagemService;
        this.armazenamentoService = armazenamentoService;
        this.ramService = ramService;
        this.precificacaoService = precificacaoService;
    }

    @GetMapping
    public ModelAndView inicial(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("/produto/index.html");
        mv.addObject("produtos", produtoService.findAll());
        CompraDTO compraDTO;
        if (request.getSession().getAttribute("compra") == null)
            compraDTO = new CompraDTO();
        else
            compraDTO = (CompraDTO) request.getSession().getAttribute("compra");

        request.getSession().setAttribute("compra", compraDTO);
        return mv;
    }

    @GetMapping("/teste")
    public ResponseEntity<?> testeinicial() {
        return new ResponseEntity<>(produtoService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/detalhes/{hash}")
    public ModelAndView detalhes(@PathVariable("hash") UUID hash, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("/produto/detalhes.html");
        Optional<Produto> produto = produtoService.findById(hash);
        if (produto.isPresent())
            mv.addObject("produto", produto.get());
        else
            System.out.println("Erro");
        return mv;
    }

    @GetMapping("/all")
    public List<Produto> produtos() {
        return produtoService.findAll();
    }

    //Verificado
    @GetMapping("/cadastro")
    public ModelAndView cadastro() {
        ModelAndView mv = new ModelAndView("/produto/cadastro.html");
        mv.addObject("produto", new ProdutoDTO());
        mv.addObject("precificacoes", precificacaoService.findAll());
        return mv;
    }

    @GetMapping("/editar/{hash}")
    public ModelAndView editar(@PathVariable("hash") UUID hash) {
        ModelAndView mv = new ModelAndView("/produto/cadastro.html");
        mv.addObject("precificacoes", precificacaoService.findAll());
        Optional<Produto> produtoOptional = produtoService.findById(hash);
        if (produtoOptional.isPresent())
            mv.addObject("produto", produtoOptional.get());
        else
            System.out.println("erro");
        return mv;
    }

    @PostMapping("/cadastro")
    public ModelAndView cadastroPost(@Valid @ModelAttribute("produto") ProdutoDTO produtoDTO,
                                     BindingResult erros,
                                     @ModelAttribute("removeImagem") Optional<String> removeImagem,
                                     @ModelAttribute("removeArmazenamento") Optional<String> removeArmazenamento,
                                     @ModelAttribute("removeRam") Optional<String> removeRam,
                                     @ModelAttribute("add") Optional<String> add) {

        if (removeImagem.isPresent())
            if (!removeImagem.get().equals("") && removeImagem.get() != null) {
                int i = Integer.parseInt(removeImagem.get());
                ImagemDTO imagemDTO = produtoDTO.getImagemList().get(i);
                if (imagemDTO.getId() != null)
                    imagemService.delete(ImagemDTO.dtoToObjeto(imagemDTO));
                produtoDTO.rmImagem(i);

                ModelAndView mv = new ModelAndView("/produto/cadastro.html");
                mv.addObject("precificacoes", precificacaoService.findAll());
                mv.addObject("produto", produtoDTO);
                mv.addObject("erros", erros.getAllErrors());
                return mv;
            }
        if (removeArmazenamento.isPresent())
            if (!removeArmazenamento.get().equals("") && removeArmazenamento.get() != null) {
                int i = Integer.parseInt(removeArmazenamento.get());
                ArmazenamentoDTO armazenamentoDTO = produtoDTO.getNotebook().getArmazenamentoList().get(i);
                if (armazenamentoDTO.getId() != null)
                    armazenamentoService.delete(ArmazenamentoDTO.dtoToObjeto(armazenamentoDTO));

                produtoDTO.getNotebook().rmArmazenamento(i);
                ModelAndView mv = new ModelAndView("/produto/cadastro.html");
                mv.addObject("produto", produtoDTO);
                mv.addObject("precificacoes", precificacaoService.findAll());
                mv.addObject("erros", erros.getAllErrors());
                return mv;
            }
        if (removeRam.isPresent())
            if (!removeRam.get().equals("") && removeRam.get() != null) {
                int i = Integer.parseInt(removeRam.get());
                RAMDTO ramdto = produtoDTO.getNotebook().getRamList().get(i);
                if (ramdto.getId() != null)
                    ramService.delete(RAMDTO.dtoToObjeto(ramdto));
                produtoDTO.getNotebook().rmRam(i);
                ModelAndView mv = new ModelAndView("/produto/cadastro.html");
                mv.addObject("produto", produtoDTO);
                mv.addObject("precificacoes", precificacaoService.findAll());
                mv.addObject("erros", erros.getAllErrors());
                return mv;
            }

        if (add.isPresent())
            if (!add.get().equals("") && add.get() != null) {
                switch (add.get()) {
                    case "armazenamento":
                        produtoDTO.getNotebook().addEmptyArmazenamento();
                        break;
                    case "ram":
                        produtoDTO.getNotebook().addEmptyRam();
                        break;
                    case "imagem":
                        produtoDTO.addEmptyImagem();
                        break;
                }
                ModelAndView mv = new ModelAndView("/produto/cadastro.html");
                mv.addObject("produto", produtoDTO);
                mv.addObject("precificacoes", precificacaoService.findAll());
                mv.addObject("erros", erros.getAllErrors());
                return mv;
            }

        if (erros.hasErrors() || produtoDTO.getPrecificacao().getId() == null) {
            ModelAndView mv = new ModelAndView("/produto/cadastro.html");
            mv.addObject("produto", produtoDTO);
            mv.addObject("precificacoes", precificacaoService.findAll());
            mv.addObject("erros", erros.getAllErrors());
            mv.addObject("precificacaoErro", "Insira um grupo de precificacao");
            return mv;
        }
        Produto produto = produtoService.save(ProdutoDTO.dtoToObjeto(produtoDTO));

        if (produtoDTO.getId() == null) {
            ModelAndView mv = new ModelAndView("redirect:/adm/produtos");
            return mv;
        } else {
            ModelAndView mv = new ModelAndView("redirect:/adm/detalheProduto/" + produto.getId());
            mv.addObject("produto", ProdutoDTO.objetoToDto(produto));
            return mv;
        }
    }

    @GetMapping("/negarHabilitado/{hash}")
    public ModelAndView desabilitarProduto(@PathVariable("hash") UUID hash) {
        produtoService.inverterHabilitado(hash);
        return new ModelAndView("redirect:/adm/produtos");
    }

    @GetMapping("/precificacao/cadastro")
    public ModelAndView cadastroPrecificacao() {
        ModelAndView mv = new ModelAndView("/produto/precificacao.html");
        mv.addObject("precificacao", new PrecificacaoDTO());
        return mv;
    }

    @PostMapping("/precificacao/cadastro")
    public ModelAndView cadastroPrecificacaoPost(@Valid @ModelAttribute("precificacao") PrecificacaoDTO precificacaoDTO,
                                                 BindingResult erros) {
        if (erros.hasErrors()) {
            ModelAndView mv = new ModelAndView("/produto/precificacao.html");
            mv.addObject("precificacao", precificacaoDTO);
            mv.addObject("erros", erros.getAllErrors());
            return mv;
        }
        precificacaoService.save(PrecificacaoDTO.dtoToObjeto(precificacaoDTO));
        return new ModelAndView("redirect:/adm/produtos");
    }

    @GetMapping("/precificacao/editar/{hash}")
    public ModelAndView cadastroPrecificacaoEditar(@PathVariable("hash") UUID hash) {
        ModelAndView mv = new ModelAndView("/produto/precificacao.html");
        Optional<Precificacao> precificacaoDTOOptional = precificacaoService.findById(hash);
        if (precificacaoDTOOptional.isPresent())
            mv.addObject("precificacao", precificacaoDTOOptional.get());
        else
            System.out.println("ERRO");
        return mv;
    }

}
