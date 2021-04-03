package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.administrador.controle;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.Cliente;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.dto.ClienteDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.servico.ClienteServico;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Compra;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.dto.CompraDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.servico.CompraServico;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Produto;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.servico.PrecificacaoService;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.servico.ProdutoService;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.servico.UsuarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * author LucasDonizeti
 */

@RestController
@RequestMapping("/adm")
public class AdministradorController {

    private final ProdutoService produtoService;
    private final PrecificacaoService precificacaoService;
    private final UsuarioServico usuarioServico;
    private final ClienteServico clienteServico;
    private final CompraServico compraServico;

    @Autowired
    public AdministradorController(ProdutoService produtoService, PrecificacaoService precificacaoService,
                                   UsuarioServico usuarioServico, ClienteServico clienteServico, CompraServico compraServico) {
        this.produtoService = produtoService;
        this.precificacaoService = precificacaoService;
        this.usuarioServico = usuarioServico;
        this.clienteServico = clienteServico;
        this.compraServico = compraServico;
    }

    @PreAuthorize("hasAuthority('ROLE_ADM')")
    @GetMapping("/visaoGeral")
    public ModelAndView home(){
        ModelAndView mv=new ModelAndView("/adm/visaoGeral.html");
        return mv;
    }

    @GetMapping("/clientes")
    public ModelAndView clientes(){
        ModelAndView mv=new ModelAndView("/adm/clientes.html");
        List<Cliente> clientes=clienteServico.findAll();
        List<ClienteDTO> clienteList=new ArrayList<>();
        clientes.forEach(u->{
                clienteList.add(ClienteDTO.objetoToDto(u));
        });
        mv.addObject("clientes", clienteList);
        return mv;
    }

    //verificado
    @GetMapping("/produtos")
    public ModelAndView produtos(){
        ModelAndView mv=new ModelAndView("/adm/produtos.html");
        mv.addObject("produtos", produtoService.findAll());
        mv.addObject("precificacoes", precificacaoService.findAll());
        return mv;
    }

    //
    @GetMapping("/detalheProduto/{hash}")
    public ModelAndView detalheProduto(@PathVariable("hash") UUID hash){
        ModelAndView mv=new ModelAndView("/adm/detalheProduto.html");
        Optional<Produto> produto = produtoService.findById(hash);
        if (produto.isPresent())
            mv.addObject("produto", produto.get());
        else
            System.out.println("Erro");
        return mv;
    }

    @GetMapping("/detalheProduto/editar/{hash}")
    public ModelAndView detalheProdutoEditar(@PathVariable("hash") UUID hash){
        ModelAndView mv=new ModelAndView("/produto/cadastro.html");
        mv.addObject("produto", produtoService.findById(hash));
        return mv;
    }

    @GetMapping("/pedidos")
    public ModelAndView pedidos(){
        ModelAndView mv=new ModelAndView("/adm/pedidos.html");
        List<CompraDTO> compraDTOList=new ArrayList<>();
        compraServico.findHabilitado().forEach(c->{
            compraDTOList.add(CompraDTO.objetoToDto(c));
        });
        mv.addObject("pedidos", compraDTOList);
        return mv;
    }

    @GetMapping("/pedidos/detalhe/{hash}")
    public ModelAndView detalhePedido(@PathVariable("hash") UUID hash){
        ModelAndView mv=new ModelAndView("/adm/detalhePedido.html");
        mv.addObject("compra", CompraDTO.objetoToDto(compraServico.findById(hash).get()));
        return mv;
    }

    @GetMapping("/pedidos/detalhe/{hash}/nextStage")
    public ModelAndView detalhePedidoNextStage(@PathVariable("hash") UUID hash){
        ModelAndView mv=new ModelAndView("/adm/detalhePedido.html");
        Compra compra= compraServico.findById(hash).get();
        compraServico.setStatus(compra, compraServico.nextAdm(compra.getStatus()));

        mv.addObject("compra", CompraDTO.objetoToDto(compraServico.findById(hash).get()));
        return mv;
    }



}
