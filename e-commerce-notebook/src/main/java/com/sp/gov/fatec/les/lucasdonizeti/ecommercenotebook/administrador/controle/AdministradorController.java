package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.administrador.controle;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.Cliente;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.dto.ClienteDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.servico.ClienteServico;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Compra;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Status;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.dto.CompraDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.servico.CompraServico;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.CupomPromocional;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.dto.CupomPromocionalDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.servico.CupomPromocionalService;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Produto;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.servico.PrecificacaoService;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.servico.ProdutoService;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.TipoUsuario;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.Usuario;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.dto.UsuarioDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.servico.UsuarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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
    private final CupomPromocionalService cupomPromocionalService;

    @Autowired
    public AdministradorController(ProdutoService produtoService, PrecificacaoService precificacaoService,
                                   UsuarioServico usuarioServico, ClienteServico clienteServico, CompraServico compraServico, CupomPromocionalService cupomPromocionalService) {
        this.produtoService = produtoService;
        this.precificacaoService = precificacaoService;
        this.usuarioServico = usuarioServico;
        this.clienteServico = clienteServico;
        this.compraServico = compraServico;
        this.cupomPromocionalService = cupomPromocionalService;
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
        compra= compraServico.findById(hash).get();
        if (compra.getStatus() == Status.TROCA_CONCLUIDA)
            compraServico.concluirTroca(compra);

        mv.addObject("compra", CompraDTO.objetoToDto(compra));
        return mv;
    }

    @GetMapping("/pedidos/detalhe/{hash}/nextStage/troca/{acao}")
    public ModelAndView detalhePedidoNextStageTroca(@PathVariable("hash") UUID hash,
                                                    @PathVariable("acao") Boolean acao){
        ModelAndView mv=new ModelAndView("/adm/detalhePedido.html");
        Compra compra= compraServico.findById(hash).get();
        compraServico.setStatus(compra, compraServico.nextTrocaAdm(compra.getStatus(), acao));

        mv.addObject("compra", CompraDTO.objetoToDto(compraServico.findById(hash).get()));
        return mv;
    }

    @GetMapping("/cadastro")
    public ModelAndView cadastroAdmin(){
        ModelAndView mv = new ModelAndView("/adm/admCadastro.html");
        UsuarioDTO usuarioDTO=new UsuarioDTO();
        usuarioDTO.setTipoUsuario(TipoUsuario.ADMINISTRADOR);
        mv.addObject("usuario",usuarioDTO);
        return mv;
    }

    @PostMapping("/cadastro")
    public ModelAndView postCadastroAdmin(@Valid @ModelAttribute("usuario")UsuarioDTO usuarioDTO,
                                          BindingResult erros){
        usuarioDTO.setTipoUsuario(TipoUsuario.ADMINISTRADOR);
        if (erros.hasErrors()){
            ModelAndView mv = new ModelAndView("/adm/admCadastro.html");
            mv.addObject("usuario", usuarioDTO);
            mv.addObject("erros", erros.getAllErrors());
            return mv;
        }
        Usuario usuario = usuarioServico.save(UsuarioDTO.dtoToObjeto(usuarioDTO));

        System.out.println("usuario criado: " + usuario.getLogin());
        return new ModelAndView("/adm/admCadastro.html");
    }

    @GetMapping("/cupoms")
    public ModelAndView cupoms(){
        ModelAndView mv = new ModelAndView("/adm/cupoms.html");
        List<CupomPromocionalDTO> cupomPromocionalDTOS=new ArrayList<>();
        cupomPromocionalService.findHabilitado().forEach(c->{
            cupomPromocionalDTOS.add(CupomPromocionalDTO.objetoToDto(c));
        });
        mv.addObject("cupomList",cupomPromocionalDTOS);
        return mv;
    }

    @GetMapping("/cupoms/cadastro")
    public ModelAndView cadCupoms(){
        ModelAndView mv = new ModelAndView("/adm/cadCupoms.html");
        mv.addObject("cupom",new CupomPromocionalDTO());
        return mv;
    }

    @GetMapping("/cupoms/editar/{hash}")
    public ModelAndView editCupoms(@PathVariable("hash")UUID id){
        Optional<CupomPromocional> cupomPromocionalOptional=cupomPromocionalService.findById(id);
        if (cupomPromocionalOptional.isPresent()){
            ModelAndView mv = new ModelAndView("/adm/cadCupoms.html");
            mv.addObject("cupom",cupomPromocionalOptional.get());
            return mv;
        }
        return new ModelAndView("redirect:/adm/cupoms");
    }

    @GetMapping("/cupoms/deletar/{hash}")
    public ModelAndView deleteCupoms(@PathVariable("hash")UUID id){
        Optional<CupomPromocional> cupomPromocionalOptional=cupomPromocionalService.findById(id);
        if (cupomPromocionalOptional.isPresent())
            cupomPromocionalService.delete(cupomPromocionalOptional.get());
        return new ModelAndView("redirect:/adm/cupoms");
    }

    @PostMapping("/cupoms/cadastro")
    public ModelAndView postcadCupoms(@Valid @ModelAttribute("cupom") CupomPromocionalDTO cupomPromocionalDTO,
                                   BindingResult erros){
        if (erros.hasErrors()){
            ModelAndView mv = new ModelAndView("/adm/cadCupoms.html");
            mv.addObject("cupom", cupomPromocionalDTO);
            mv.addObject("erros", erros.getAllErrors());
            return mv;
        }
        cupomPromocionalService.save(CupomPromocionalDTO.dtoToObjeto(cupomPromocionalDTO));
        return new ModelAndView("redirect:/adm/cupoms");
    }



}
