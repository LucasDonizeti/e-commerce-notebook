package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.controle;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao.Bandeira;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao.Cartao;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.Cliente;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.Genero;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.TipoCliente;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Compra;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Frete;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Pagamento;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Status;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.servico.CompraServico;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.Cupom;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.TipoCupom;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento.Documento;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento.TipoDocumento;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.*;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.*;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Precificacao;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Produto;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.servico.ProdutoService;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.telefone.Telefone;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.telefone.TipoTelefone;
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
@RequestMapping("/compra")
public class CompraController {
    private final CompraServico compraServico;
    private final ProdutoService produtoService;

    @Autowired
    public CompraController(CompraServico compraServico, ProdutoService produtoService) {
        this.compraServico = compraServico;
        this.produtoService = produtoService;
    }

@GetMapping("/realizar-compra")
public ModelAndView realizarCompra(){
        ModelAndView mv =new ModelAndView("/compra/realizarCompra.html");
        Compra compra=new Compra();
        compra.setCliente(genCliente());
        compra.setProdutos(produtoService.findAll());
        mv.addObject("compra", compra);
        return mv;
}
    @GetMapping("/confirmar-compra/teste")
    public ResponseEntity<?> realizarCompraTeste(){
        return new ResponseEntity<>(genCompra(), HttpStatus.OK);
    }

    @GetMapping("/confirmar-compra")
    public ModelAndView confirmarCompra(){
        ModelAndView mv =new ModelAndView("compra/confirmarCompra.html");
        mv.addObject("compra", genCompra());
        return mv;
    }

    @GetMapping("/cli/detalheCompra")
    public ModelAndView detalheCompraCli(){
        ModelAndView mv =new ModelAndView("compra/cliDetalheCompra.html");
        mv.addObject("compra", genCompra());
        return mv;
    }


    private Compra genCompra() {

        Compra compra=new Compra();
        compra.setProdutos(produtoService.findAll());

        Frete frete=new Frete();
        Cliente cliente=genCliente();
        frete.setEndereco(cliente.getEnderecoList().get(0));
        frete.setValor(15F);
        compra.setFrete(frete);

        Pagamento pagament=new Pagamento();
        pagament.setCartao(cliente.getCartaoList().get(0));
        pagament.setValor(11515-10f);
        List<Pagamento> pagamentoList=new ArrayList<>();
        pagamentoList.add(pagament);
        compra.setPagamentos(pagamentoList);

        List<Cupom> cupomList=new ArrayList<>();
        cupomList.add(cliente.getCupomList().get(0));
        compra.setCupoms(cupomList);

        compra.setCliente(cliente);

        compra.setStatus(Status.EM_PROCESSAMENTO);

        return compra;
    }

    private Cliente genCliente(){
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
        usuario.setLogin("teste@gmail.com");
        usuario.setSenha("1234aaBB$");
        usuario.setNome("Cliente teste");

        cliente.setUsuario(usuario);

        List<Endereco> enderecoList=new ArrayList<>();
        Endereco endereco=new Endereco();

        endereco.setCep("08940000");
        endereco.setRua("rua 1");
        endereco.setBairro("Mogilar");
        endereco.setLongradouro(Longradouro.JARDIM);
        endereco.setTipoResidencia(TipoResidencia.CASA);

        Cidade cidade=new Cidade();
        cidade.setNome("Mogi das Cruzes");

        cidade.setEstado(Estado.SAO_PAULO);

        endereco.setCidade(cidade);
        endereco.setNumero("10");
        enderecoList.add(endereco);

        Endereco endereco1=new Endereco();

        endereco1.setCep("04563254");
        endereco1.setRua("rua 2");
        endereco1.setBairro("Centro");
        endereco1.setLongradouro(Longradouro.JARDIM);
        endereco1.setTipoResidencia(TipoResidencia.CASA);

        Cidade cidade1=new Cidade();
        cidade1.setNome("Guaruja");

        cidade1.setEstado(Estado.SAO_PAULO);

        endereco1.setCidade(cidade1);
        endereco1.setNumero("55");
        enderecoList.add(endereco1);

        cliente.setEnderecoList(enderecoList);

        Documento documento2=new Documento();
        documento2.setTipoDocumento(TipoDocumento.RG);
        documento2.setCodigo("5358412362");
        documentoList.add(documento2);
        cliente.setDocumentos(documentoList);

        List<Cartao> cartaoList=new ArrayList<>();

        Cartao c1=new Cartao();
        c1.setBandeira(Bandeira.AMARICANEXPRESS);
        c1.setNome("Cliente American Express Cartao");
        c1.setNumero("3451 084599 64008");
        c1.setCvv("9285");
        Cartao c2=new Cartao();
        c2.setBandeira(Bandeira.MASTERCARD);
        c2.setNome("Cliente 1 cartao");
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

        return cliente;
    }

    private Produto genProduto(){
        Notebook notebook = new Notebook();
        notebook.setCodigo("81FE0002BR");
        notebook.setMarca("Lenovo");
        notebook.setModelo("Ideapad 330");

        notebook.setSo(SO.WINDOWS10HOME);

        Tela tela = new Tela();
        tela.setTamanho("15,6");
        tela.setClock("60");

        notebook.setCategoria(Categoria.ENTRADA);
        CPU cpu = new CPU();
        cpu.setModelo("i5");
        notebook.setCpu(cpu);
        GPU gpu=new GPU();
        gpu.setModelo("UHD graphics 620");
        notebook.setGpu(gpu);
        notebook.setTela(tela);

        List<RAM> ramList=new ArrayList<>();
        RAM ram =new RAM();
        ram.setClock("2666");
        ram.setMemoria(8);
        ramList.add(ram);
        notebook.setRamList(ramList);

        List<Armazenamento> armazenamentoList=new ArrayList<>();
        Armazenamento armazenamento = new Armazenamento();
        armazenamento.setMemoria(1000);
        armazenamento.setTipoArmazenamento(TipoArmazenamento.HD);
        armazenamentoList.add(armazenamento);

        notebook.setArmazenamentoList(armazenamentoList);

        Produto produto=new Produto();
        produto.setEstoque(10);
        produto.setCusto(2000F);
        produto.setPontuacaoCliente(25);

        Precificacao precificacao=new Precificacao();

        precificacao.setNome("Baixa margem de lucro");
        precificacao.setMargemDeLucro(0.15F);
        produto.setPrecificacao(precificacao);

        produto.setNotebook(notebook);

        return produto;
    }
}
