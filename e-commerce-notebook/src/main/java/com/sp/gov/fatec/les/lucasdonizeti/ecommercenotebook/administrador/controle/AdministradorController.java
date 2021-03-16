package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.administrador.controle;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao.Bandeira;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao.Cartao;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.Cliente;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.Genero;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.TipoCliente;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.dto.ClienteDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.Cupom;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.TipoCupom;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento.Documento;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento.TipoDocumento;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.*;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.Categoria;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.SO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.TipoArmazenamento;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.dto.*;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.dto.ImagemDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.dto.PrecificacaoDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.dto.ProdutoDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.telefone.Telefone;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.telefone.TipoTelefone;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.TipoUsuario;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.Usuario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * author LucasDonizeti
 */

@RestController
@RequestMapping("/adm")
public class AdministradorController {

    @GetMapping("/visaoGeral")
    public ModelAndView home(){
        ModelAndView mv=new ModelAndView("/adm/visaoGeral.html");
        return mv;
    }

    @GetMapping("/clientes")
    public ModelAndView clientes(){
        ModelAndView mv=new ModelAndView("/adm/clientes.html");
        List<ClienteDTO> clienteDTOS=new ArrayList<>();
        clienteDTOS.add(ClienteDTO.objetoToDto(genCliente2()));
        clienteDTOS.add(ClienteDTO.objetoToDto(genCliente()));
        mv.addObject("clientes", clienteDTOS);
        return mv;
    }

    @GetMapping("/produtos")
    public ModelAndView produtos(){
        ModelAndView mv=new ModelAndView("/adm/produtos.html");
        return mv;
    }

    @GetMapping("/detalheProduto")
    public ModelAndView detalheProduto(){
        ModelAndView mv=new ModelAndView("/adm/detalheProduto.html");
        return mv;
    }

    @GetMapping("/detalheProduto/editar")
    public ModelAndView detalheProdutoEditar(){
        ModelAndView mv=new ModelAndView("/produto/cadastro.html");
        mv.addObject("produto", genProduto());
        return mv;
    }

    @GetMapping("/pedidos")
    public ModelAndView pedidos(){
        ModelAndView mv=new ModelAndView("/adm/pedidos.html");
        return mv;
    }

    @GetMapping("/pedidos/detalhe")
    public ModelAndView detalhePedido(){
        ModelAndView mv=new ModelAndView("/adm/detalhePedido.html");
        return mv;
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

        telefone.setDdd("011");
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
        documento2.setCodigo("535886263");
        documentoList.add(documento2);
        cliente.setDocumentos(documentoList);

        List<Cartao> cartaoList=new ArrayList<>();

        Cartao c1=new Cartao();
        c1.setBandeira(Bandeira.AMARICANEXPRESS);
        c1.setNome("Cliente American Express Cartao");
        c1.setNumero("345108459964008");
        c1.setCvv("9285");
        Cartao c2=new Cartao();
        c2.setBandeira(Bandeira.MASTERCARD);
        c2.setNome("Cliente 1 cartao");
        c2.setNumero("5458072591767327");
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
    private Cliente genCliente2(){
        Cliente cliente=new Cliente();
        cliente.setTipoCliente(TipoCliente.JURIDICA);
        cliente.setDataNascimento(LocalDate.of(2000,7,26));
        cliente.setRank(900);
        List<Documento> documentoList=new ArrayList<>();
        Documento documento=new Documento();
        documento.setTipoDocumento(TipoDocumento.CPF);
        documento.setCodigo("44536522832");
        documentoList.add(documento);
        cliente.setGenero(Genero.MASCULINO);
        Telefone telefone=new Telefone();

        telefone.setDdd("011");
        telefone.setNumero("985372928");
        telefone.setTipoTelefone(TipoTelefone.MOVEL);

        cliente.setTelefone(telefone);

        Usuario usuario=new Usuario();

        usuario.setTipoUsuario(TipoUsuario.CLIENTE);
        usuario.setLogin("empresa@gmail.com");
        usuario.setSenha("1234aaBB$");
        usuario.setNome("Cliente juridico");

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
        documento2.setCodigo("535886263");
        documentoList.add(documento2);
        cliente.setDocumentos(documentoList);

        List<Cartao> cartaoList=new ArrayList<>();

        Cartao c1=new Cartao();
        c1.setBandeira(Bandeira.AMARICANEXPRESS);
        c1.setNome("Cliente American Express Cartao");
        c1.setNumero("345108459964008");
        c1.setCvv("9285");

        cartaoList.add(c1);

        cliente.setCartaoList(cartaoList);

        List<Cupom> cupomList=new ArrayList<>();

        Cupom cupom2 = new Cupom();
        cupom2.setValor(7000l);
        cupom2.setTipoCupom(TipoCupom.PROMOCIONAL);

        cupomList.add(cupom2);

        cliente.setCupomList(cupomList);

        return cliente;
    }

    private ProdutoDTO genProduto(){
        NotebookDTO avell = new NotebookDTO();

        avell.setCodigo("293877");
        avell.setMarca("AVELL");
        avell.setModelo("C62 RTX LIV");

        avell.setSo(SO.WINDOWS10PRO);

        TelaDTO tela1 = new TelaDTO();
        tela1.setTamanho("17,3");
        tela1.setClock("144");

        avell.setCategoria(Categoria.PROFISSIONAL);
        CPUDTO cpu1 = new CPUDTO();
        cpu1.setModelo("i7");
        avell.setCpu(cpu1);
        GPUDTO gpu1=new GPUDTO();
        gpu1.setModelo("NVIDIA® GeForce® RTX 2060");
        avell.setGpu(gpu1);
        avell.setTela(tela1);

        List<RAMDTO> rams=new ArrayList<>();
        RAMDTO ram1 =new RAMDTO();
        ram1.setClock("2666");
        ram1.setMemoria(8);
        rams.add(ram1);

        RAMDTO ram2 =new RAMDTO();
        ram2.setClock("2666");
        ram2.setMemoria(8);
        rams.add(ram2);

        avell.setRamList(rams);

        List<ArmazenamentoDTO> armazenamentos=new ArrayList<>();
        ArmazenamentoDTO armazenamento1 = new ArmazenamentoDTO();
        armazenamento1.setMemoria(1000);
        armazenamento1.setTipoArmazenamento(TipoArmazenamento.HD);

        ArmazenamentoDTO armazenamento2 = new ArmazenamentoDTO();
        armazenamento2.setMemoria(500);
        armazenamento2.setTipoArmazenamento(TipoArmazenamento.SSD);


        armazenamentos.add(armazenamento2);
        armazenamentos.add(armazenamento1);

        avell.setArmazenamentoList(armazenamentos);

        ProdutoDTO produto1=new ProdutoDTO();
        produto1.setEstoque(7);
        produto1.setCusto(8000F);
        produto1.setPontuacaoCliente(75);

        PrecificacaoDTO precificacao=new PrecificacaoDTO();

        precificacao.setNome("Baixa margem de lucro");
        precificacao.setMargemDeLucro(0.15F);

        produto1.setPrecificacao(precificacao);

        ImagemDTO imagem4=new ImagemDTO();
        imagem4.setLink("https://images.avell.com.br/media/catalog/product/cache/1/thumbnail/800x600/9df78eab33525d08d6e5fb8d27136e95/c/6/c62_10_.jpg");

        ImagemDTO imagem5=new ImagemDTO();
        imagem5.setLink("https://images.avell.com.br/media/catalog/product/cache/1/thumbnail/800x600/9df78eab33525d08d6e5fb8d27136e95/c/6/c62_12_.jpg");

        ImagemDTO imagem6=new ImagemDTO();
        imagem6.setLink("https://images.avell.com.br/media/catalog/product/cache/1/thumbnail/800x600/9df78eab33525d08d6e5fb8d27136e95/c/6/c62_7_.jpg");

        ImagemDTO imagem7=new ImagemDTO();
        imagem7.setLink("https://images.avell.com.br/media/catalog/product/cache/1/thumbnail/800x600/9df78eab33525d08d6e5fb8d27136e95/c/6/c62_5_.jpg");
        produto1.addImagem(imagem4);
        produto1.addImagem(imagem5);
        produto1.addImagem(imagem6);
        produto1.addImagem(imagem7);
        produto1.setHash(UUID.randomUUID());

        produto1.setNotebook(avell);
        return produto1;
    }
}
