package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.controle;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao.dto.CartaoDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao.servico.CartaoSarvice;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.dto.ClienteDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.servico.ClienteServico;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Compra;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.dto.*;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.servico.CompraServico;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.CupomPromocional;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.dto.CupomPromocionalDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.dto.CupomTrocaDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.servico.CupomPromocionalService;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.servico.CupomTrocaService;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.Endereco;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.dto.EnderecoDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.servico.EnderecoServico;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.dto.ProdutoDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.servico.ProdutoService;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.Usuario;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.servico.UsuarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * author LucasDonizeti
 */

@RestController
@RequestMapping("/compra")
public class CompraController {
    private final CompraServico compraServico;
    private final ProdutoService produtoService;
    private final ClienteServico clienteServico;
    private final UsuarioServico usuarioServico;
    private final EnderecoServico enderecoServico;
    private final CartaoSarvice cartaoSarvice;
    private final CupomPromocionalService cupomPromocionalService;
    private final CupomTrocaService cupomTrocaService;


    @Autowired
    public CompraController(CompraServico compraServico, ProdutoService produtoService, ClienteServico clienteServico, UsuarioServico usuarioServico, EnderecoServico enderecoServico, CartaoSarvice cartaoSarvice, CupomPromocionalService cupomPromocionalService, CupomTrocaService cupomTrocaService) {
        this.compraServico = compraServico;
        this.produtoService = produtoService;
        this.clienteServico = clienteServico;
        this.usuarioServico = usuarioServico;

        this.enderecoServico = enderecoServico;
        this.cartaoSarvice = cartaoSarvice;
        this.cupomPromocionalService = cupomPromocionalService;
        this.cupomTrocaService = cupomTrocaService;
    }

    @PreAuthorize("hasAuthority('ROLE_CLI')")
    @GetMapping("/realizar-compra/{compraID}")
    public ModelAndView realizarCompra(HttpServletRequest request, HttpServletResponse response,
                                       @PathVariable("compraID") Optional<UUID> compraID) {
        ModelAndView mv = new ModelAndView("/compra/realizarCompra.html");
        CompraDTO compraDTO = CompraDTO.objetoToDto(compraServico.findById(compraID.get()).get());


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        Optional<Usuario> usuarioOptional = usuarioServico.findByLogin(login);
        if (usuarioOptional.isPresent()) {
            compraDTO.setCliente(ClienteDTO.objetoToDto(clienteServico.findByUsuarioId(usuarioOptional.get().getId()).get()));

            compraDTO.getCliente().getCartoes().forEach(c -> {
                PagamentoDTO pagamentoDTO = new PagamentoDTO();
                pagamentoDTO.setCartao(c);
                pagamentoDTO.setValor(0f);
                compraDTO.getPagamentos().add(pagamentoDTO);
            });
        }

        mv.addObject("compra", compraDTO);
        return mv;
    }

    @PreAuthorize("hasAuthority('ROLE_CLI')")
    @GetMapping("/realizar-compra")
    public ModelAndView realizarCompra2(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("/compra/realizarCompra.html");
        CompraDTO compraDTO = (CompraDTO) request.getSession().getAttribute("compra");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        Optional<Usuario> usuarioOptional = usuarioServico.findByLogin(login);
        if (usuarioOptional.isPresent()) {
            compraDTO.setCliente(ClienteDTO.objetoToDto(clienteServico.findByUsuarioId(usuarioOptional.get().getId()).get()));
            compraDTO.getPagamentos().clear();
            compraDTO.getCliente().getCartoes().forEach(c -> {
                PagamentoDTO pagamentoDTO = new PagamentoDTO();
                pagamentoDTO.setCartao(c);
                pagamentoDTO.setValor(0f);
                compraDTO.getPagamentos().add(pagamentoDTO);
            });
            compraDTO.getCupomsDeTroca().clear();
            cupomTrocaService.findByClienteId(compraDTO.getCliente().getId()).forEach(c -> {
                compraDTO.getCupomsDeTroca().add(CupomTrocaSelecionadoDTO.objetoToDto(c));
            });
        }

        mv.addObject("compra", compraDTO);
        return mv;
    }

    @PreAuthorize("hasAuthority('ROLE_CLI')")
    @PostMapping("/realizar-compra")
    public ModelAndView realizarCompraPost(@Valid @ModelAttribute("compra") CompraDTO compraDTO,
                                           BindingResult erro,
                                           @ModelAttribute("verifica") Optional<String> verifica) {


        compraDTO = preparaCompraDTO(compraDTO);

        if (verifica.isPresent())
            if (!verifica.get().equals("") && verifica.get() != null) {
                if (verifica.get().equals("cupomPromocional")) {
                    compraDTO=verificaCupomPromocional(compraDTO, verifica.get());
                    return preparaRealizaCompra(compraDTO, erro);
                }
            }

        if (erro.hasErrors() || compraDTO.getFrete().getEndereco().getId() == null) {
            return preparaRealizaCompra(compraDTO, erro);
        }

        if (compraDTO.getCupomPromocional().getId()==null)
            compraDTO.setCupomPromocional(null);

        if (!verificaPagamento(compraDTO)) {
            return preparaRealizaCompra(compraDTO, erro);
        }

        compraDTO=beforeCompraPreprare(compraDTO);
        compraDTO = CompraDTO.objetoToDto(compraServico.save(CompraDTO.dtoToObjeto(compraDTO)));
        cupomPromocionalService.subtrairUso(compraDTO.getCupomPromocional().getId());

        ModelAndView mv = new ModelAndView("/compra/confirmarCompra.html");
        compraDTO = CompraDTO.objetoToDto(compraServico.setStatus(CompraDTO.dtoToObjeto(compraDTO), compraServico.nextValidSystem(compraDTO.getStatus())));
        mv.addObject("compra", compraDTO);
        return mv;
    }

    private CompraDTO vefificaEnderecoFrete(CompraDTO compraDTO){
        if (compraDTO.getFrete().getEndereco().getId() != null) {
            FreteDTO freteDTO = new FreteDTO();
            Optional<Endereco> endereco = enderecoServico.findById(compraDTO.getFrete().getEndereco().getId());
            if (endereco.isPresent()) {
                freteDTO.setEndereco(EnderecoDTO.objetoToDto(endereco.get()));
                freteDTO.setValor(15f);
                compraDTO.setFrete(freteDTO);
            }
        }

        return compraDTO;
    }
    private CompraDTO verificaCupomPromocional(CompraDTO compraDTO, String codigoCupom){

        Optional<CupomPromocional> cupomPromocionalOptional = cupomPromocionalService.findUtilizavelByCodigo(codigoCupom);
        if (cupomPromocionalOptional.isPresent())
            compraDTO.setCupomPromocional(CupomPromocionalDTO.objetoToDto(cupomPromocionalOptional.get()));

        return compraDTO;
    }
    private Boolean verificaPagamento(CompraDTO compraDTO){
        System.out.println("valor de compra " + compraDTO.getValorDeCompra().intValue());
        System.out.println("total pago " + compraDTO.getTotalPago().intValue());
        System.out.println(compraDTO.getValorDeCompra().intValue() == compraDTO.getTotalPago().intValue());
        return compraDTO.getValorDeCompra().intValue() == compraDTO.getTotalPago().intValue();
    }
    private CompraDTO preparaCompraDTO(CompraDTO compraDTO){
        compraDTO=vefificaEnderecoFrete(compraDTO);
        for(ItemDTO item : compraDTO.getItens()){
            item.setProduto(ProdutoDTO.objetoToDto(produtoService.findById(item.getProduto().getId()).get()));
        }
        for (PagamentoDTO p:compraDTO.getPagamentos()){
            p.setCartao(CartaoDTO.objetoToDto(cartaoSarvice.findById(p.getCartao().getId()).get()));
        }
        for (CupomTrocaDTO c:compraDTO.getCupomsDeTroca()){
            CupomTrocaDTO cupomTrocaDTO = CupomTrocaDTO.objetoToDto(cupomTrocaService.findById(c.getId()).get());
            c.setValor(cupomTrocaDTO.getValor());
            c.setCodigo(cupomTrocaDTO.getCodigo());
            c.setId(cupomTrocaDTO.getId());
        }
        compraDTO.setCliente(ClienteDTO.objetoToDto(clienteServico.findById(compraDTO.getCliente().getId()).get()));
        return compraDTO;
    }
    private ModelAndView preparaRealizaCompra(CompraDTO compraDTO, BindingResult erro){
        ModelAndView mv = new ModelAndView("/compra/realizarCompra.html");
        mv.addObject("compra", compraDTO);
        mv.addObject("erros", erro);
        return mv;
    }
    private CompraDTO beforeCompraPreprare(CompraDTO compraDTO){
        List<CupomTrocaDTO> cupomTrocaDTOS=new ArrayList<>();
        cupomTrocaDTOS.addAll(compraDTO.getCupomsDeTroca());
        for (CupomTrocaDTO c:cupomTrocaDTOS){
            if (c.getHabilitado()==false){
                compraDTO.getCupomsDeTroca();
            }
        }
        List<PagamentoDTO> pagamentoDTOS= new ArrayList<>();
        pagamentoDTOS.addAll(compraDTO.getPagamentos());
        for (PagamentoDTO p:pagamentoDTOS){
            if (p.getHabilitado()==false){
                compraDTO.getPagamentos().remove(p);
            }
        }
        return compraDTO;
    }


    @PreAuthorize("hasAuthority('ROLE_CLI')")
    @GetMapping("/cli/detalheCompra/{hash}")
    public ModelAndView detalheCompraCli(@PathVariable("hash") UUID hash) {
        ModelAndView mv = new ModelAndView("compra/cliDetalheCompra.html");
        Optional<Compra> compraOptional = compraServico.findById(hash);
        mv.addObject("compra", CompraDTO.objetoToDto(compraOptional.get()));
        return mv;
    }

    @PreAuthorize("hasAuthority('ROLE_CLI')")
    @GetMapping("/cli/detalheCompra/{hash}/nextStage")
    public ModelAndView detalhePedidoNextStage(@PathVariable("hash") UUID hash) {
        ModelAndView mv = new ModelAndView("compra/cliDetalheCompra.html");
        Compra compra = compraServico.findById(hash).get();
        compraServico.setStatus(compra, compraServico.nextCli(compra.getStatus()));

        mv.addObject("compra", CompraDTO.objetoToDto(compraServico.findById(hash).get()));
        return mv;
    }

}
