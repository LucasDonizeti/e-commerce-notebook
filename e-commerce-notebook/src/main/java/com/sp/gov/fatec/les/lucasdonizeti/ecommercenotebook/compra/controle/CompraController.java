package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.controle;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao.dto.CartaoDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao.servico.CartaoSarvice;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.dto.ClienteDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.servico.ClienteServico;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Compra;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Frete;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Item;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Status;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.dto.*;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.infrastructure.frete.FreteAPIDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.infrastructure.frete.FreteClientAPI;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.servico.CompraServico;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.CupomPromocional;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.CupomTroca;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import java.util.stream.Collectors;

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
            cupomTrocaService.findByClienteIdAndHabilitadoTrue(compraDTO.getCliente().getId()).forEach(c -> {
                c.setHabilitado(false);
                compraDTO.getCupomsDeTroca().add(CupomTrocaSelecionadoDTO.objetoToDto(c));
            });
        }


        for (int x = 0; x < compraDTO.getItens().size(); x++) {
            compraDTO.getItens().get(x).setPrecoDeVendaProdutos(compraDTO.getItens().get(x).getProduto().getPrecoDeVenda());
        }


        mv.addObject("compra", compraDTO);
        return mv;
    }

    @PreAuthorize("hasAuthority('ROLE_CLI')")
    @PostMapping("/realizar-compra")
    public ModelAndView realizarCompraPost(@Valid @ModelAttribute("compra") CompraDTO compraDTO,
                                           BindingResult erro,
                                           @ModelAttribute("verifica") Optional<String> verifica,
                                           HttpServletRequest request,
                                           HttpServletResponse response) {


        compraDTO = preparaCompraDTO(compraDTO);

        if (verifica.isPresent())
            if (!verifica.get().equals("") && verifica.get() != null) {
                if (verifica.get().equals("cupomPromocional")) {
                    compraDTO = verificaCupomPromocional(compraDTO, compraDTO.getCupomPromocional().getCodigo());
                    return preparaRealizaCompra(compraDTO, erro);
                }
            }

        if (erro.hasErrors() || compraDTO.temItemSemFrete()) {
            return preparaRealizaCompra(compraDTO, erro);
        }

        if (compraDTO.getCupomPromocional().getId() == null)
            compraDTO.setCupomPromocional(null);

        if (!verificaPagamento(compraDTO)) {
            return preparaRealizaCompra(compraDTO, erro);
        }

        compraDTO = beforeCompraPreprare(compraDTO);

        compraDTO = CompraDTO.objetoToDto(compraServico.save(CompraDTO.dtoToObjeto(compraDTO)));
        for (ItemDTO i : compraDTO.getItens()){
            produtoService.removerEstoque(i.getProduto().getId(), i.getQuantidade());
        }
        if (compraDTO.getCupomPromocional() != null)
            cupomPromocionalService.subtrairUso(compraDTO.getCupomPromocional().getId());
        for (CupomTrocaDTO cupomTrocaDTO:compraDTO.getCupomsDeTroca()){
            cupomTrocaDTO.setHabilitado(false);
            CupomTroca cupom=CupomTrocaDTO.dtoToObjeto(cupomTrocaDTO);
            cupom.setCliente(ClienteDTO.dtoToObjeto(compraDTO.getCliente()));
            cupomTrocaService.save(cupom);
        }

        request.getSession().setAttribute("compra", new CompraDTO());

        ModelAndView mv = new ModelAndView("/compra/confirmarCompra.html");
        compraDTO = CompraDTO.objetoToDto(compraServico.setStatusCompraItem(CompraDTO.dtoToObjeto(compraDTO), compraServico.nextValidSystem(compraDTO.getStatus())));
        FreteDTO freteDTO = new FreteDTO();
        freteDTO.setEndereco(compraDTO.getItens().get(0).frete.getEndereco());
        compraDTO.setFrete(freteDTO);
        mv.addObject("compra", compraDTO);
        return mv;
    }

    private CompraDTO vefificaEnderecoFrete(CompraDTO compraDTO) {
        if (compraDTO.getFrete().getEndereco().getId() != null) {
            Optional<Endereco> endereco = enderecoServico.findById(compraDTO.getFrete().getEndereco().getId());
            if (endereco.isPresent())
                for (int x = 0; x < compraDTO.getItens().size(); x++) {
                    Frete frete = new Frete();
                    frete.setEndereco(endereco.get());
                    FreteAPIDTO freteAPIDTO = FreteClientAPI.findFreteByCorreios("01153000", frete.getEndereco().getCep(), compraDTO.getItens().get(x).getPrecoDeVendaProdutos());
                    frete = FreteAPIDTO.mergeFrete(freteAPIDTO, frete);
                    compraDTO.getItens().get(x).setFrete(FreteDTO.objetoToDto(frete));
                }
        }

        return compraDTO;
    }

    private CompraDTO verificaCupomPromocional(CompraDTO compraDTO, String codigoCupom) {
        Optional<CupomPromocional> cupomPromocionalOptional = cupomPromocionalService.findUtilizavelByCodigo(codigoCupom);
        if (cupomPromocionalOptional.isPresent())
            compraDTO.setCupomPromocional(CupomPromocionalDTO.objetoToDto(cupomPromocionalOptional.get()));
        return compraDTO;
    }

    private Boolean verificaPagamento(CompraDTO compraDTO) {
        return compraDTO.getValorDeCompra().intValue() == compraDTO.getTotalPago().intValue();
    }

    private CompraDTO preparaCompraDTO(CompraDTO compraDTO) {
        for (ItemDTO item : compraDTO.getItens()) {
            item.setProduto(ProdutoDTO.objetoToDto(produtoService.findById(item.getProduto().getId()).get()));
            item.setPrecoDeVendaProdutos(item.getProduto().getPrecoDeVenda());
        }
        for (PagamentoDTO p : compraDTO.getPagamentos()) {
            p.setCartao(CartaoDTO.objetoToDto(cartaoSarvice.findById(p.getCartao().getId()).get()));
        }
        for (CupomTrocaDTO c : compraDTO.getCupomsDeTroca()) {
            CupomTrocaDTO cupomTrocaDTO = CupomTrocaDTO.objetoToDto(cupomTrocaService.findById(c.getId()).get());
            c.setValor(cupomTrocaDTO.getValor());
            c.setCodigo(cupomTrocaDTO.getCodigo());
            c.setId(cupomTrocaDTO.getId());
        }
        compraDTO.setCliente(ClienteDTO.objetoToDto(clienteServico.findById(compraDTO.getCliente().getId()).get()));
        compraDTO.calcularValores();
        compraDTO = vefificaEnderecoFrete(compraDTO);
        compraDTO.calcularValores();
        return compraDTO;
    }

    private ModelAndView preparaRealizaCompra(CompraDTO compraDTO, BindingResult erro) {
        ModelAndView mv = new ModelAndView("/compra/realizarCompra.html");
        mv.addObject("compra", compraDTO);
        mv.addObject("erros", erro);
        return mv;
    }

    private CompraDTO beforeCompraPreprare(CompraDTO compraDTO) {
        List<CupomTrocaDTO> cupomTrocaDTOS = new ArrayList<>();
        cupomTrocaDTOS.addAll(compraDTO.getCupomsDeTroca());
        for (CupomTrocaDTO c : cupomTrocaDTOS) {
            if (c.getHabilitado() == false) {
                compraDTO.getCupomsDeTroca().remove(c);
            }
        }
        List<PagamentoDTO> pagamentoDTOS = new ArrayList<>();
        pagamentoDTOS.addAll(compraDTO.getPagamentos());
        for (PagamentoDTO p : pagamentoDTOS) {
            if (p.getHabilitado() == false) {
                compraDTO.getPagamentos().remove(p);
            }
        }
        for (ItemDTO i : compraDTO.getItens())
            i.getFrete().setId(null);

        return compraDTO;
    }


    @PreAuthorize("hasAuthority('ROLE_CLI')")
    @GetMapping("/cli/detalheCompra/{hash}")
    public ModelAndView detalheCompraCli(@PathVariable("hash") UUID hash) {
        ModelAndView mv = new ModelAndView("compra/cliDetalheCompra.html");
        Compra compra = compraServico.findById(hash).get();
        CompraDTO compraDTO = CompraDTO.objetoToDto(compra);
        FreteDTO freteDTO = new FreteDTO();
        freteDTO.setEndereco(compraDTO.getItens().get(0).frete.getEndereco());
        compraDTO.setFrete(freteDTO);
        Boolean nextStageCompra = (compra.getStatus() == Status.ENTREGUE || compra.getStatus() == Status.TROCA_NAO_AUTORIZADA);
        for (int x = 0; x < compra.getItens().size(); x++) {
            if (compra.getItens().get(x).getStatus() != compra.getStatus() && nextStageCompra)
                nextStageCompra = false;
        }


        mv.addObject("compra", compraDTO);
        mv.addObject("podeNextStageCompra", nextStageCompra);
        return mv;
    }

    @PreAuthorize("hasAuthority('ROLE_CLI')")
    @GetMapping("/cli/detalheCompra/{hash}/nextStage")
    public ModelAndView detalhePedidoNextStage(@PathVariable("hash") UUID hash) {
        Compra compra = compraServico.findById(hash).get();
        compraServico.setStatusCompraItem(compra, compraServico.nextCli(compra.getStatus()));
        return new ModelAndView("redirect:/compra/cli/detalheCompra/" + hash);
    }

    @PreAuthorize("hasAuthority('ROLE_CLI')")
    @GetMapping("/cli/detalheCompra/{hash}/item/{hashitem}")
    public ModelAndView detalheItemNextStage(@PathVariable("hash") UUID hash,
                                             @PathVariable("hashitem") UUID hashitem) {

        Compra compra = compraServico.findById(hash).get();
        Optional<Item> itemOptional = compra.getItens().stream().filter(i -> i.getId().equals(hashitem)).findFirst();
        if (itemOptional.isPresent()) {
            ModelAndView mv = new ModelAndView("compra/cliDetalheCompraItem.html");
            mv.addObject("compra", CompraDTO.objetoToDto(compra));
            mv.addObject("item", ItemDTO.objetoToDto(itemOptional.get()));
            return mv;
        } else {
            return new ModelAndView("redirect:/compra/cli/detalheCompra/" + hash);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_CLI')")
    @PostMapping("/cli/detalheCompra/{hash}/item/{hashitem}/troca")
    public ModelAndView detalheItemNextStagePost(@PathVariable("hash") UUID hash,
                                                 @PathVariable("hashitem") UUID hashitem,
                                                 @ModelAttribute("quantidade") Optional<String> quantidadeTroca) {
        Compra compra = compraServico.findById(hash).get();
        for (int x = 0; x < compra.getItens().size(); x++) {
            if (compra.getItens().get(x).getId().equals(hashitem)) {
                compra.getItens().get(x).setStatus(compraServico.nextCli(compra.getItens().get(x).getStatus()));
                if (compra.getItens().get(x).getStatus() == Status.EM_TROCA)
                    if (compra.getItens().get(x).getQuantidade() >= Integer.parseInt(quantidadeTroca.get()))
                        compra.getItens().get(x).setQuantidadeEmTroca(Integer.parseInt(quantidadeTroca.get()));
                compraServico.save(compra);
            }
        }
        return new ModelAndView("redirect:/compra/cli/detalheCompra/" + hash);
    }
}
