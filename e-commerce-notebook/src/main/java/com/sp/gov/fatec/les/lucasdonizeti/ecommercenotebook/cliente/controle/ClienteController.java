package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.controle;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao.dto.CartaoDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao.servico.CartaoSarvice;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.Cliente;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.dto.ClienteDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.servico.ClienteServico;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Status;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.dto.CompraDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.dto.ItemDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.servico.CompraServico;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.servico.CupomTrocaService;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento.dto.DocumentoDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento.servico.DocumentoService;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.Endereco;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.dto.EnderecoDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.infrestructure.cep.CepAPIDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.infrestructure.cep.CepClientAPI;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.servico.EnderecoServico;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.dto.ProdutoDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.servico.ProdutoService;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.Usuario;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.dto.MudarSenhaDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.servico.UsuarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteServico clienteServico;
    private final ProdutoService produtoService;
    private final DocumentoService documentoService;
    private final UsuarioServico usuarioServico;
    private final CartaoSarvice cartaoSarvice;
    private final EnderecoServico enderecoServico;
    private final CompraServico compraServico;
    private final CupomTrocaService cupomTrocaService;

    @Autowired
    public ClienteController(ClienteServico clienteServico, ProdutoService produtoService,
                             DocumentoService documentoService, UsuarioServico usuarioServico,
                             CartaoSarvice cartaoSarvice, EnderecoServico enderecoServico, CompraServico compraServico, CupomTrocaService cupomTrocaService) {
        this.clienteServico = clienteServico;
        this.produtoService = produtoService;
        this.documentoService = documentoService;
        this.usuarioServico = usuarioServico;
        this.cartaoSarvice = cartaoSarvice;
        this.enderecoServico = enderecoServico;
        this.compraServico = compraServico;
        this.cupomTrocaService = cupomTrocaService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> all() {
        return new ResponseEntity<>(clienteServico.findAll(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Cliente cliente) {
        return new ResponseEntity<>(clienteServico.save(cliente), HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody Cliente cliente) {
        clienteServico.delete(cliente);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Refatorar
    @GetMapping("/carrinho")
    public ModelAndView carrinhoDeCompra(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("/cliente/carrinho.html");
        if (request.getSession().getAttribute("compra") == null){
            request.getSession().setAttribute("compra", new CompraDTO());
        }
        CompraDTO compraDTO= (CompraDTO) request.getSession().getAttribute("compra");
        compraDTO.calcularValores();
        mv.addObject("compra", compraDTO);
        return mv;
    }

    //Refatorar
    @GetMapping("/carrinho/{hash}")
    public ModelAndView carrinhoDeCompraLogado(HttpServletRequest request, HttpServletResponse response,
                                               @PathVariable("hash") UUID hash, @AuthenticationPrincipal Usuario usuario) {
        ModelAndView mv = new ModelAndView("/cliente/carrinho.html");
        mv.addObject("usuarioLogado", clienteServico.findByUsuarioId(usuario.getId()).get());
        CompraDTO compraDTO = (CompraDTO) request.getSession().getAttribute("compra");
        compraDTO.setCliente(ClienteDTO.objetoToDto(clienteServico.findByUsuarioId(usuario.getId()).get()));
        request.getSession().setAttribute("compra", compraDTO);
        mv.addObject("compra", compraDTO);
        return mv;
    }

    //Refatorar
    @PostMapping("/carrinho/qtd/{i}")
    public ModelAndView carrinhoDeCompraQuantidade(HttpServletRequest request, HttpServletResponse response,
                                                   @RequestParam("n") String n, @PathVariable("i") String i) {
        ModelAndView mv = new ModelAndView("/cliente/carrinho.html");
        CompraDTO compraDTO = (CompraDTO) request.getSession().getAttribute("compra");
        compraDTO.setQuantidade(Integer.parseInt(i), Integer.parseInt(n));

        request.getSession().setAttribute("compra", compraDTO);

        mv.addObject("compra", compraDTO);
        return mv;
    }

    @PostMapping("/carrinho/exc/{i}")
    public ModelAndView carrinhoDeCompraExcluir(HttpServletRequest request, HttpServletResponse response,
                                                @PathVariable("i") String i) {
        ModelAndView mv = new ModelAndView("/cliente/carrinho.html");
        CompraDTO compraDTO = (CompraDTO) request.getSession().getAttribute("compra");
        compraDTO.excProduto(Integer.parseInt(i));
        request.getSession().setAttribute("compra", compraDTO);
        mv.addObject("compra", compraDTO);
        return mv;
    }

    //Refatorar
    @PostMapping("/carrinho/{hash}")
    public ModelAndView addCarrinho(HttpServletRequest request, HttpServletResponse response, @PathVariable("hash") UUID hash) {
        ModelAndView mv = new ModelAndView("redirect:/cliente/carrinho");
        CompraDTO compraDTO = (CompraDTO) request.getSession().getAttribute("compra");
        ItemDTO item = new ItemDTO();
        item.setProduto(ProdutoDTO.objetoToDto(produtoService.findById(hash).get()));
        item.setStatus(Status.REPROVADA);
        compraDTO.addProduto(item);
        request.getSession().setAttribute("compra", compraDTO);
        mv.addObject("compra", compraDTO);
        return mv;
    }

    //Varificado
    @PreAuthorize("hasAuthority('ROLE_CLI')")
    @GetMapping("/cli/editarPerfil")
    public ModelAndView editarPerfil(@AuthenticationPrincipal Usuario usuario) {
        ModelAndView mv = new ModelAndView("/cliente/cadastro.html");
        Optional<Cliente> cliente = clienteServico.findByUsuarioId(usuario.getId());

        if (!cliente.isPresent()) {
            System.out.println("cliente não está presente");
            mv.addObject("clienteDTO", new ClienteDTO());
        } else {
            mv.addObject("clienteDTO", ClienteDTO.objetoToDto(cliente.get()));
        }
        return mv;
    }

    //Verificado
    @GetMapping("/cadastro")
    public ModelAndView cadastro(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("/cliente/cadastro.html");
        mv.addObject("clienteDTO", new ClienteDTO());
        return mv;
    }

    //Verificado
    @PostMapping("/cadastro")
    public ModelAndView cadastroPost(@Valid @ModelAttribute("clienteDTO") ClienteDTO clienteDTO,
                                     BindingResult erros,
                                     @ModelAttribute("removeDocumento") Optional<String> removeDocumento,
                                     @ModelAttribute("removeCartao") Optional<String> removeCartao,
                                     @ModelAttribute("removeEndereco") Optional<String> removeEndereco,
                                     @ModelAttribute("senhaRepetida") Optional<String> senhaRepetida,
                                     @ModelAttribute("add") Optional<String> add,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {

        if (removeDocumento.isPresent())
            if (!removeDocumento.get().equals("") && removeDocumento.get() != null) {
                int i = Integer.parseInt(removeDocumento.get());
                DocumentoDTO documentoDTO = clienteDTO.getDocumentos().get(i);
                if (documentoDTO.getId() != null)
                    documentoService.delete(DocumentoDTO.dtoToObjeto(documentoDTO));
                clienteDTO.rmDocumento(i);

                ModelAndView mv = new ModelAndView("/cliente/cadastro.html");
                mv.addObject("clienteDTO", clienteDTO);
                mv.addObject("senhaRapetida", senhaRepetida);
                mv.addObject("erros", erros.getAllErrors());
                return mv;
            }
        if (removeCartao.isPresent())
            if (!removeCartao.get().equals("") && removeCartao.get() != null) {
                int i = Integer.parseInt(removeCartao.get());
                CartaoDTO cartaoDTO = clienteDTO.getCartoes().get(i);
                if (cartaoDTO.getId() != null)
                    cartaoSarvice.delete(CartaoDTO.dtoToObjeto(cartaoDTO));
                clienteDTO.rmCartao(i);

                ModelAndView mv = new ModelAndView("/cliente/cadastro.html");
                mv.addObject("clienteDTO", clienteDTO);
                mv.addObject("senhaRapetida", senhaRepetida);
                mv.addObject("erros", erros.getAllErrors());
                return mv;
            }
        if (removeEndereco.isPresent())
            if (!removeEndereco.get().equals("") && removeEndereco.get() != null) {
                int i = Integer.parseInt(removeEndereco.get());
                EnderecoDTO enderecoDTO = clienteDTO.getEnderecos().get(i);
                if (enderecoDTO.getId() != null)
                    enderecoServico.remove(EnderecoDTO.dtoToObjeto(enderecoDTO));
                clienteDTO.rmEndereco(i);

                ModelAndView mv = new ModelAndView("/cliente/cadastro.html");
                mv.addObject("clienteDTO", clienteDTO);
                mv.addObject("senhaRapetida", senhaRepetida);
                mv.addObject("erros", erros.getAllErrors());
                return mv;
            }

        if (add.isPresent())
            switch (add.get()) {
                case "documento":
                    clienteDTO.addEmptyDocumento();
                    break;
                case "cartao":
                    clienteDTO.addEmptyCartao();
                    break;
                case "endereco":
                    clienteDTO.addEmptyEndereco();
                    break;
            }
        clienteDTO.setEnderecos(autoCompletarEnderecosPeloCEP(clienteDTO.getEnderecos()));

        if (!senhaRepetida.get().equals(clienteDTO.getUsuario().getSenha()) && clienteDTO.getId() == null) {
            System.out.println("senha repetida");
            ModelAndView mv = new ModelAndView("/cliente/cadastro.html");
            mv.addObject("clienteDTO", clienteDTO);
            mv.addObject("senhaDiferenteException", "Senha diferente");
            mv.addObject("senhaRapetida", senhaRepetida);
            mv.addObject("erros", erros.getAllErrors());
            return mv;
        }

        if (erros.hasErrors() || !add.get().equals("")) {
            ModelAndView mv = new ModelAndView("/cliente/cadastro.html");
            mv.addObject("clienteDTO", clienteDTO);
            mv.addObject("senhaRapetida", senhaRepetida);
            mv.addObject("erros", erros.getAllErrors());
            return mv;
        }
        Cliente cl = clienteServico.save(ClienteDTO.dtoToObjeto(clienteDTO));

        if (clienteDTO.getUsuario().getId() == null) {
            ModelAndView mv = new ModelAndView("redirect:/usuario/login");
            mv.addObject("usuarioDto", clienteDTO.getUsuario());
            return mv;
        } else {
            ModelAndView mv = new ModelAndView("redirect:/cliente/cli/perfil/");
            return mv;
        }
    }

    private List<EnderecoDTO> autoCompletarEnderecosPeloCEP(List<EnderecoDTO> enderecoDTOS) {
        return enderecoDTOS.stream()
                .map(e -> autoCompleteEnderecoDTOByCEP(e))
                .collect(Collectors.toList());
    }

    private EnderecoDTO autoCompleteEnderecoDTOByCEP(EnderecoDTO e) {
        Endereco endereco = EnderecoDTO.dtoToObjeto(e);
        Optional<CepAPIDTO> cepAPIDTO = CepClientAPI.findCepByViaCepAPI(endereco.getCep());
        if (cepAPIDTO.isPresent())
            if (!cepAPIDTO.get().getErro()) {
                endereco = CepAPIDTO.mergeEndereco(endereco, cepAPIDTO.get());
            }

        return EnderecoDTO.objetoToDto(endereco);
    }


    //Verificado
    @PreAuthorize("hasAuthority('ROLE_CLI')")
    @GetMapping("/cli/perfil")
    public ModelAndView perfil(@AuthenticationPrincipal Usuario usuario) {
        ModelAndView mv = new ModelAndView("/cliente/cliPerfil.html");
        mv.addObject("cliente", ClienteDTO.objetoToDto(clienteServico.findByUsuarioId(usuario.getId()).get()));
        return mv;
    }


    @PreAuthorize("hasAuthority('ROLE_CLI')")
    @GetMapping("/cli/acompanharPedidos")
    public ModelAndView acompanharPedidos(@AuthenticationPrincipal Usuario usuario) {
        ModelAndView mv = new ModelAndView("/cliente/cliAcompanharPedidos.html");
        Cliente cliente = clienteServico.findByUsuarioId(usuario.getId()).get();
        mv.addObject("cliente", ClienteDTO.objetoToDto(cliente));
        List<CompraDTO> compraDTOList = new ArrayList<>();
        compraServico.findCompraHabilitadoByClienteId(cliente.getId()).forEach(c -> {
            compraDTOList.add(CompraDTO.objetoToDto(c));
        });
        mv.addObject("pedidos", compraDTOList);
        return mv;
    }

    //Verificado
    @PreAuthorize("hasAuthority('ROLE_CLI')")
    @GetMapping("/cli/mudarSenha")
    public ModelAndView mudarSenhaCli(@AuthenticationPrincipal Usuario usuario) {
        ModelAndView mv = new ModelAndView("cliente/cliMudarSenha.html");
        mv.addObject("cliente", ClienteDTO.objetoToDto(clienteServico.findByUsuarioId(usuario.getId()).get()));
        Optional<Cliente> clienteOptional = clienteServico.findByUsuarioId(usuario.getId());
        if (clienteOptional.isPresent()) {
            MudarSenhaDTO mudarSenhaDTO = new MudarSenhaDTO();
            mudarSenhaDTO.setId(clienteOptional.get().getId());
            mv.addObject("usuario", mudarSenhaDTO);
        } else {
            System.out.println("Erro");
            mv.addObject("usuario", new MudarSenhaDTO());
        }
        return mv;
    }

    //Varificado
    @PreAuthorize("hasAuthority('ROLE_CLI')")
    @PostMapping("/cli/mudarSenha")
    public ModelAndView postMudarSenhaCli(@Valid @ModelAttribute("usuario") MudarSenhaDTO mudarSenhaDTO,
                                          BindingResult erros) {

        //Varificar se tem erro ou se a senha repetida == nova
        if (!mudarSenhaDTO.getRepetida().equals(mudarSenhaDTO.getNova())) {
            ModelAndView mv = new ModelAndView("/cliente/cliMudarSenha.html");
            mv.addObject("cliente", ClienteDTO.objetoToDto(clienteServico.findById(mudarSenhaDTO.getId()).get()));
            mv.addObject("usuario", mudarSenhaDTO);
            mv.addObject("senhaDiferenteException", "Senha diferente");
            mv.addObject("erros", erros.getAllErrors());
            return mv;
        }

        if (erros.hasErrors()) {
            ModelAndView mv = new ModelAndView("/cliente/cliMudarSenha.html");
            mv.addObject("cliente", ClienteDTO.objetoToDto(clienteServico.findById(mudarSenhaDTO.getId()).get()));
            mv.addObject("usuario", mudarSenhaDTO);
            mv.addObject("erros", erros.getAllErrors());
            return mv;
        }
        //varificar senha antiga

        Optional<Cliente> clienteOptional = clienteServico.findById(mudarSenhaDTO.getId());
        if (clienteOptional.isPresent()) {
            if (BCrypt.checkpw(mudarSenhaDTO.getAntiga(), clienteOptional.get().getUsuario().getPassword())) {
                Usuario usuario = clienteOptional.get().getUsuario();
                usuario.setSenha(mudarSenhaDTO.getNova());
                usuarioServico.save(usuario);

                ModelAndView mv = new ModelAndView("redirect:/cliente/cli/perfil/");
                return mv;
            } else {
                ModelAndView mv = new ModelAndView("/cliente/cliMudarSenha.html");
                mv.addObject("cliente", ClienteDTO.objetoToDto(clienteServico.findById(mudarSenhaDTO.getId()).get()));
                mv.addObject("usuario", mudarSenhaDTO);
                mv.addObject("senhaErradaException", "Senha errada");
                mv.addObject("erros", erros.getAllErrors());
                return mv;
            }
        }

        System.out.println("Erro");
        ModelAndView mv = new ModelAndView("redirect:/cliente/cli/perfil");
        return mv;
    }

    @PreAuthorize("hasAuthority('ROLE_CLI')")
    @GetMapping("/cli/cupoms")
    public ModelAndView cupoms(@AuthenticationPrincipal Usuario usuario) {

        Cliente cliente = clienteServico.findByUsuarioId(usuario.getId()).get();

        ModelAndView mv = new ModelAndView("/cliente/cliCupoms.html");
        mv.addObject("cupoms", cupomTrocaService.findByClienteId(cliente.getId()));
        return mv;
    }
}

