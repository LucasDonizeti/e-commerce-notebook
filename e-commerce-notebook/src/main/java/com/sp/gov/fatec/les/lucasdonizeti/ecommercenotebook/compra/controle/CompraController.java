package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.controle;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao.servico.CartaoSarvice;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.dto.ClienteDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.servico.ClienteServico;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Compra;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.dto.CompraDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.dto.CupomTrocaSelecionadoDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.dto.FreteDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.dto.PagamentoDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.servico.CompraServico;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.CupomPromocional;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.dto.CupomPromocionalDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.dto.CupomTrocaDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.servico.CupomPromocionalService;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.Endereco;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.dto.EnderecoDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.servico.EnderecoServico;
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


    @Autowired
    public CompraController(CompraServico compraServico, ProdutoService produtoService, ClienteServico clienteServico, UsuarioServico usuarioServico, EnderecoServico enderecoServico, CartaoSarvice cartaoSarvice, CupomPromocionalService cupomPromocionalService) {
        this.compraServico = compraServico;
        this.produtoService = produtoService;
        this.clienteServico = clienteServico;
        this.usuarioServico = usuarioServico;

        this.enderecoServico = enderecoServico;
        this.cartaoSarvice = cartaoSarvice;
        this.cupomPromocionalService = cupomPromocionalService;
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

            compraDTO.getCliente().getCartoes().forEach(c -> {
                PagamentoDTO pagamentoDTO = new PagamentoDTO();
                pagamentoDTO.setCartao(c);
                pagamentoDTO.setValor(0f);
                compraDTO.getPagamentos().add(pagamentoDTO);
            });
            compraDTO.getCliente().getCupomsTroca().forEach(c -> {
                compraDTO.getCupomsDeTroca().add(CupomTrocaSelecionadoDTO.objetoToDto(CupomTrocaDTO.dtoToObjeto(c)));
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

        if (verifica.isPresent())
            if (!verifica.get().equals("") && verifica.get() != null) {
                if (verifica.get().equals("cupomPromocional")) {
                    String codigo = compraDTO.getCupomPromocional().getCodigo();

                    Optional<CupomPromocional> cupomPromocionalOptional = cupomPromocionalService.findByCodigo(codigo);
                    if (cupomPromocionalOptional.isPresent())
                        compraDTO.setCupomPromocional(CupomPromocionalDTO.objetoToDto(cupomPromocionalOptional.get()));

                    ModelAndView mv = new ModelAndView("/compra/realizarCompra.html");
                    mv.addObject("compra", compraDTO);
                    mv.addObject("erros", erro);
                    return mv;
                }
            }

        if (compraDTO.getFrete().getEndereco().getId() != null) {
            FreteDTO freteDTO = new FreteDTO();
            Optional<Endereco> endereco = enderecoServico.findById(compraDTO.getFrete().getEndereco().getId());
            if (endereco.isPresent()) {
                freteDTO.setEndereco(EnderecoDTO.objetoToDto(endereco.get()));
                freteDTO.setValor(15f);
                compraDTO.setFrete(freteDTO);
            }
        }

        if (erro.hasErrors()) {
            ModelAndView mv = new ModelAndView("/compra/realizarCompra.html");
            mv.addObject("compra", compraDTO);
            mv.addObject("erros", erro);
            return mv;
        }
        compraDTO = CompraDTO.objetoToDto(compraServico.save(CompraDTO.dtoToObjeto(compraDTO)));
        System.out.println(compraDTO.getValorDeCompra());
        System.out.println(compraDTO.getTotalPago());

        if (compraDTO.getValorDeCompra().intValue() != compraDTO.getTotalPago().intValue()) {
            ModelAndView mv = new ModelAndView("/compra/realizarCompra.html");
            mv.addObject("compra", compraDTO);
            mv.addObject("erros", erro);
            mv.addObject("PagamentoRejeitadoException", "Pagamento não pode ser efetuado dessa forma");
            return mv;
        }


        ModelAndView mv = new ModelAndView("/compra/confirmarCompra.html");
        compraDTO = CompraDTO.objetoToDto(compraServico.setStatus(CompraDTO.dtoToObjeto(compraDTO), compraServico.nextValidSystem(compraDTO.getStatus())));
        mv.addObject("compra", compraDTO);
        return mv;
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
