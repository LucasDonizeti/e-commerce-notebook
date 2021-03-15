package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.controle;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao.Bandeira;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao.Cartao;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao.dto.CartaoDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.Cliente;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.Genero;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.TipoCliente;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.dto.ClienteDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.servico.ClienteServico;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.Cupom;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.TipoCupom;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento.Documento;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento.TipoDocumento;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento.dto.DocumentoDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.*;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.dto.CidadeDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.dto.EnderecoDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.servico.ProdutoService;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.telefone.Telefone;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.telefone.TipoTelefone;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.telefone.dto.TelefoneDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.TipoUsuario;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.Usuario;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * author LucasDonizeti
 */

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteServico clienteServico;
    private final ProdutoService produtoService;

    @Autowired
    public ClienteController(ClienteServico clienteServico, ProdutoService produtoService) {
        this.clienteServico = clienteServico;
        this.produtoService = produtoService;
    }
    @GetMapping
    public ResponseEntity<?> teste(){
        Cliente cliente=new Cliente();
        cliente.setTipoCliente(TipoCliente.FISICA);
        cliente.setDataNascimento(LocalDate.of(2000,7,26));
        cliente.setRank(55);
        List<Documento> documentoList=new ArrayList<>();
        Documento documento=new Documento();
        documento.setTipoDocumento(TipoDocumento.CPF);
        documento.setCodigo("44536522832");
        documentoList.add(documento);
        cliente.setGenero(Genero.MASCULINO);
        Telefone telefone=new Telefone();

        telefone.setDdd("11");
        telefone.setNumero("985372928");
        telefone.setTipoTelefone(TipoTelefone.MOVEL);

        cliente.setTelefone(telefone);

        Usuario usuario=new Usuario();

        usuario.setTipoUsuario(TipoUsuario.CLIENTE);
        usuario.setLogin("lr3@gmail.com");
        usuario.setSenha("lr3");
        usuario.setNome("Lucas Don");

        cliente.setUsuario(usuario);

        List<Endereco> enderecoList=new ArrayList<>();
        Endereco endereco=new Endereco();

        endereco.setCep("08940000");
        endereco.setBairro("Jardim dos eucaliptos");
        endereco.setLongradouro(Longradouro.JARDIM);
        endereco.setTipoResidencia(TipoResidencia.CASA);

        Cidade cidade=new Cidade();
        cidade.setNome("Biritiba Mirim");

        cidade.setEstado(Estado.SAO_PAULO);

        endereco.setCidade(cidade);
        endereco.setNumero("55");
        enderecoList.add(endereco);

        cliente.setEnderecoList(enderecoList);

        Documento documento2=new Documento();
        documento2.setTipoDocumento(TipoDocumento.RG);
        documento2.setCodigo("535886263");
        documentoList.add(documento2);
        cliente.setDocumentos(documentoList);

        List<Cartao> cartaoList=new ArrayList<>();

        Cartao c1=new Cartao();
        c1.setBandeira(Bandeira.AMARICANEXPRESS);
        c1.setNome("Lucas Donizeti");
        c1.setNumero("3451 084599 64008");
        c1.setCvv("9285");
        Cartao c2=new Cartao();
        c2.setBandeira(Bandeira.MASTERCARD);
        c2.setNome("Lucas Siqueira");
        c2.setNumero("5458 0725 9176 7327");
        c2.setCvv("167");

        cartaoList.add(c1);
        cartaoList.add(c2);

        cliente.setCartaoList(cartaoList);

        List<Cupom> cupomList=new ArrayList<>();
        Cupom cupom1 = new Cupom();
        cupom1.setValor(10l);
        cupom1.setTipoCupom(TipoCupom.TROCA);

        Cupom cupom2 = new Cupom();
        cupom2.setValor(70l);
        cupom2.setTipoCupom(TipoCupom.PROMOCIONAL);

        cupomList.add(cupom1);
        cupomList.add(cupom2);

        cliente.setCupomList(cupomList);

        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }
    /*
    @PostMapping
    public ResponseEntity<?> testepost(@RequestBody Cliente cliente){
        return new ResponseEntity<>(clienteServico.save(cliente), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> all(){
        return new ResponseEntity<>(clienteServico.findAll(), HttpStatus.OK);
    }
     */

    @GetMapping("/carrinho")
    public ModelAndView carrinhoDeCompra(){
        ModelAndView mv=new ModelAndView("/cliente/carrinho.html");
        mv.addObject("produtos", produtoService.findAll());
        return mv;
    }

    @GetMapping("/cadastro")
    public ModelAndView cadastro(){
        ModelAndView mv=new ModelAndView("/cliente/cadastro.html");
        mv.addObject("clienteDTO", new ClienteDTO());
        return mv;
    }

    @PostMapping("/cadastro")
    public ModelAndView cadastroPost(@Valid @ModelAttribute("clienteDTO")ClienteDTO clienteDTO,
                                     BindingResult erros,
                                     @ModelAttribute("removeDocumento") Optional<String> removeDocumento,
                                     @ModelAttribute("removeCartao") Optional<String> removeCartao,
                                     @ModelAttribute("removeEndereco") Optional<String> removeEndereco,
                                     @ModelAttribute("senhaRepetida")Optional<String> senhaRepetida,
                                     @ModelAttribute("add") Optional<String> add){

        if (removeDocumento.isPresent())
            if (!removeDocumento.get().equals("") && removeDocumento.get()!=null)
                clienteDTO.rmDocumento(Integer.parseInt(removeDocumento.get()));
        if (removeCartao.isPresent())
            if (!removeCartao.get().equals("") && removeCartao.get()!=null)
                clienteDTO.rmCartao(Integer.parseInt(removeCartao.get()));
        if (removeEndereco.isPresent())
            if (!removeEndereco.get().equals("") && removeEndereco.get()!=null)
                clienteDTO.rmEndereco(Integer.parseInt(removeEndereco.get()));

        if (add.isPresent())
            switch (add.get()){
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

        if (!senhaRepetida.get().equals(clienteDTO.getUsuario().getSenha())){
            ModelAndView mv = new ModelAndView("/cliente/cadastro.html");
            mv.addObject("clienteDTO", clienteDTO);
            mv.addObject("senhaDiferenteException", "Senha diferente");
            mv.addObject("senhaRapetida", senhaRepetida);
            mv.addObject("erros", erros.getAllErrors());
            return mv;
        }

        if (erros.hasErrors()){
            ModelAndView mv = new ModelAndView("/cliente/cadastro.html");
            mv.addObject("clienteDTO", clienteDTO);
            mv.addObject("senhaRapetida", senhaRepetida);
            mv.addObject("erros", erros.getAllErrors());
            return mv;
        }

        ModelAndView mv=new ModelAndView("redirect:/usuario/login");
        mv.addObject("usuarioDto", clienteDTO.getUsuario());
        return mv;
    }
    @GetMapping("/teste")
    public ResponseEntity<?> testeDTO(){
        ClienteDTO cliente=new ClienteDTO();
        List<EnderecoDTO> enderecoDTOList=new ArrayList<>();

        EnderecoDTO enderecoDTO=new EnderecoDTO();
        enderecoDTO.setNumero("10");
        enderecoDTO.setBairro("Bairro 1");
        enderecoDTO.setCep("08940000");
        enderecoDTO.setTipoResidencia(TipoResidencia.CASA);
        enderecoDTO.setLongradouro(Longradouro.RUA);
        CidadeDTO cidadeDTO=new CidadeDTO();
        cidadeDTO.setNome("Mogi das Cruzes");
        cidadeDTO.setEstado(Estado.SAO_PAULO);
        enderecoDTO.setCidade(cidadeDTO);
        enderecoDTOList.add(enderecoDTO);
        cliente.setEnderecos(enderecoDTOList);

        cliente.setDataNascimento(LocalDate.now());
        List<DocumentoDTO> documentoDTOS=new ArrayList<>();
        DocumentoDTO documentoDTO=new DocumentoDTO();
        documentoDTO.setTipoDocumento(TipoDocumento.RG);
        documentoDTO.setCodigo("132456789");
        documentoDTOS.add(documentoDTO);
        cliente.setDocumentos(documentoDTOS);

        cliente.setTipoCliente(TipoCliente.FISICA);
        cliente.setGenero(Genero.MASCULINO);
        List<CartaoDTO> cartaoDTOS=new ArrayList<>();
        CartaoDTO cartaoDTO=new CartaoDTO();
        cartaoDTO.setNome("cartao 1");
        cartaoDTO.setNumero("5584975692976422");
        cartaoDTO.setCvv("938");
        cartaoDTO.setBandeira(Bandeira.MASTERCARD);
        cartaoDTOS.add(cartaoDTO);
        CartaoDTO cartaoDTO2=new CartaoDTO();
        cartaoDTO2.setNome("cartao 2");
        cartaoDTO2.setNumero("5584975692976421");
        cartaoDTO2.setCvv("777");
        cartaoDTO2.setBandeira(Bandeira.AMARICANEXPRESS);
        cartaoDTOS.add(cartaoDTO2);
        cliente.setCartoes(cartaoDTOS);

        TelefoneDTO telefoneDTO=new TelefoneDTO();
        telefoneDTO.setNumero("987654321");
        telefoneDTO.setDdd("011");
        telefoneDTO.setTipoTelefone(TipoTelefone.MOVEL);
        cliente.setTelefone(telefoneDTO);

        UsuarioDTO usuarioDTO=new UsuarioDTO();
        usuarioDTO.setTipoUsuario(TipoUsuario.CLIENTE);
        usuarioDTO.setLogin("teste@gmail.com");
        usuarioDTO.setSenha("1234aaBB$");
        usuarioDTO.setNome("usuario teste");
        cliente.setUsuario(usuarioDTO);

        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

}

