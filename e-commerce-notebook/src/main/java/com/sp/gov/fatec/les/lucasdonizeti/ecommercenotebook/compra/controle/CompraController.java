package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.controle;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao.dto.CartaoDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao.servico.CartaoSarvice;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.dto.ClienteDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.servico.ClienteServico;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Compra;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Status;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.dto.CompraDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.dto.FreteDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.dto.PagamentoDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.servico.CompraServico;
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


    @Autowired
    public CompraController(CompraServico compraServico, ProdutoService produtoService, ClienteServico clienteServico, UsuarioServico usuarioServico, EnderecoServico enderecoServico, CartaoSarvice cartaoSarvice) {
        this.compraServico = compraServico;
        this.produtoService = produtoService;
        this.clienteServico = clienteServico;
        this.usuarioServico = usuarioServico;

        this.enderecoServico = enderecoServico;
        this.cartaoSarvice = cartaoSarvice;
    }

    @PreAuthorize("hasAuthority('ROLE_CLI')")
    @GetMapping("/realizar-compra/{compraID}")
    public ModelAndView realizarCompra(HttpServletRequest request, HttpServletResponse response,
                                       @PathVariable("compraID") Optional<UUID> compraID) {
        ModelAndView mv = new ModelAndView("/compra/realizarCompra.html");
        CompraDTO compraDTO = CompraDTO.objetoToDto(compraServico.findById(compraID.get()).get());


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        Optional<Usuario> usuarioOptional=usuarioServico.findByLogin(login);
        if (usuarioOptional.isPresent()) {
            compraDTO.setCliente(ClienteDTO.objetoToDto(clienteServico.findByUsuarioId(usuarioOptional.get().getId()).get()));

            compraDTO.getCliente().getCartoes().forEach(c->{
                PagamentoDTO pagamentoDTO=new PagamentoDTO();
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
        Optional<Usuario> usuarioOptional=usuarioServico.findByLogin(login);
        if (usuarioOptional.isPresent()) {
            compraDTO.setCliente(ClienteDTO.objetoToDto(clienteServico.findByUsuarioId(usuarioOptional.get().getId()).get()));

            compraDTO.getCliente().getCartoes().forEach(c->{
                PagamentoDTO pagamentoDTO=new PagamentoDTO();
                pagamentoDTO.setCartao(c);
                pagamentoDTO.setValor(0f);
                compraDTO.getPagamentos().add(pagamentoDTO);
            });
        }

        mv.addObject("compra", compraDTO);
        return mv;
    }

    @PreAuthorize("hasAuthority('ROLE_CLI')")
    @PostMapping("/realizar-compra")
    public ModelAndView realizarCompraPost(@Valid @ModelAttribute("compra") CompraDTO compraDTO,
                                           BindingResult erro) {

        System.out.println(compraDTO.getFrete().getEndereco().getId());
            if (compraDTO.getFrete().getEndereco().getId()!=null){
                FreteDTO freteDTO=new FreteDTO();
                Optional<Endereco> endereco=enderecoServico.findById(compraDTO.getFrete().getEndereco().getId());
                if (endereco.isPresent()) {
                    freteDTO.setEndereco(EnderecoDTO.objetoToDto(endereco.get()));
                    freteDTO.setValor(15f);
                    compraDTO.setFrete(freteDTO);
                }
            }

        System.out.println(compraDTO.getFrete().getEndereco().getRua());
        System.out.println(compraDTO.getFrete().getValor());
        System.out.println(compraDTO.getItens().get(0).getId());
        System.out.println(compraDTO.getItens().get(0).getQuantidade());
        System.out.println(compraDTO.getItens().get(0).getStatus().getDescricao());
        System.out.println(compraDTO.getItens().get(0).getProduto().getId());

        compraDTO=CompraDTO.objetoToDto(compraServico.save(CompraDTO.dtoToObjeto(compraDTO)));

        System.out.println(compraDTO.getValorDeCompra());
        System.out.println(compraDTO.getTotalPago());
        if (erro.hasErrors()) {
            ModelAndView mv = new ModelAndView("/compra/realizarCompra.html");
            mv.addObject("compra", compraDTO);
            mv.addObject("erros", erro);
            return mv;
        }
        if (compraDTO.getValorDeCompra().intValue() != compraDTO.getTotalPago().intValue()){
            ModelAndView mv = new ModelAndView("/compra/realizarCompra.html");
            mv.addObject("compra", compraDTO);
            mv.addObject("erros", erro);
            mv.addObject("PagamentoRejeitadoException", "Pagamento não pode ser efetuado dessa forma");
            return mv;
        }

        ModelAndView mv = new ModelAndView("/compra/confirmarCompra.html");
        compraDTO = CompraDTO.objetoToDto(compraServico.setStatus(CompraDTO.dtoToObjeto(compraDTO), Status.APROVADA));
        mv.addObject("compra", compraDTO);
        return mv;
    }

    @PreAuthorize("hasAuthority('ROLE_CLI')")
    @GetMapping("/confirmar-compra")
    public ModelAndView confirmarCompra(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("compra/confirmarCompra.html");
        CompraDTO compraDTO = (CompraDTO) request.getSession().getAttribute("compra");
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

}
