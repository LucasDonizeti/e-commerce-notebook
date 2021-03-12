package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.controle;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao.Bandeira;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao.Cartao;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.Cliente;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.Genero;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.TipoCliente;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Compra;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Frete;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Pagamento;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.Cupom;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.TipoCupom;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento.Documento;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento.TipoDocumento;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.*;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.*;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Precificacao;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Produto;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.telefone.Telefone;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.telefone.TipoTelefone;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.TipoUsuario;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * author LucasDonizeti
 */

@RestController
@RequestMapping("/compra")
public class compraController {
    @GetMapping
    public ResponseEntity<?> teste() {

        Compra compra=new Compra();
        List<Produto> produtoList=new ArrayList<>();
        produtoList.add(genProduto());
        compra.setProdutos(produtoList);

        Frete frete=new Frete();
        Cliente cliente=genCliente();
        frete.setEndereco(cliente.getEnderecoList().get(0));
        frete.setValor(25F);
        compra.setFrete(frete);

        Pagamento pagament=new Pagamento();
        pagament.setCartao(cliente.getCartaoList().get(0));
        pagament.setValor(2000F);
        List<Pagamento> pagamentoList=new ArrayList<>();
        pagamentoList.add(pagament);
        compra.setPagamentos(pagamentoList);

        compra.setCupoms(cliente.getCupomList());

        return new ResponseEntity<>(compra, HttpStatus.OK);
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
