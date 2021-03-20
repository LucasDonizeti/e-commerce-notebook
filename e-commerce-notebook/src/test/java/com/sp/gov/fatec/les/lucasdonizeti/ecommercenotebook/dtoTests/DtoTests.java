package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.dtoTests;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao.Bandeira;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao.Cartao;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao.dto.CartaoDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.Cliente;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.Genero;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.TipoCliente;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.dto.ClienteDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Compra;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Frete;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Pagamento;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Status;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.dto.CompraDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.Cupom;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.TipoCupom;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.dto.CupomDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento.Documento;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento.TipoDocumento;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento.dto.DocumentoDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.*;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.dto.CidadeDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.dto.EnderecoDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.*;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.dto.*;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Imagem;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Precificacao;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Produto;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.dto.ImagemDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.dto.PrecificacaoDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.dto.ProdutoDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.telefone.Telefone;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.telefone.TipoTelefone;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.TipoUsuario;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.Usuario;
import org.dozer.DozerBeanMapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * author LucasDonizeti
 */
public class DtoTests {

    //Documento
    @Test
    public void DocumentoObjetoToDto() {
        Documento documento = new Documento();
        documento.setCodigo("132");
        documento.setTipoDocumento(TipoDocumento.RG);

        DocumentoDTO documentoDTO = DocumentoDTO.objetoToDto(documento);

        assertEquals(documento.getCodigo(), documentoDTO.getCodigo());
        assertEquals(documento.getTipoDocumento(), documentoDTO.getTipoDocumento());
    }

    @Test
    public void DocumentoDtoToObjeto() {
        DocumentoDTO documentoDTO = new DocumentoDTO();
        documentoDTO.setCodigo("132");
        documentoDTO.setTipoDocumento(TipoDocumento.RG);

        Documento documento = DocumentoDTO.dtoToObjeto(documentoDTO);

        assertEquals(documento.getCodigo(), documentoDTO.getCodigo());
        assertEquals(documento.getTipoDocumento(), documentoDTO.getTipoDocumento());
    }

    //Endereco
    @Test
    public void EnderecoObjetoToDto() {
        Endereco endereco = new Endereco();
        endereco.setRua("rua 1");
        endereco.setNumero("10");
        endereco.setTipoResidencia(TipoResidencia.CASA);
        endereco.setCep("08940000");
        endereco.setLongradouro(Longradouro.RUA);
        endereco.setBairro("Jardim");
        Cidade cidade = new Cidade();
        cidade.setNome("Biritiba Mirim");
        cidade.setEstado(Estado.SAO_PAULO);
        endereco.setCidade(cidade);


        EnderecoDTO enderecoDTO = EnderecoDTO.objetoToDto(endereco);

        assertEquals(endereco.getCep(), enderecoDTO.getCep());
        assertEquals(endereco.getCidade().getNome(), enderecoDTO.getCidade().getNome());
        assertEquals(endereco.getCidade().getEstado(), enderecoDTO.getCidade().getEstado());

    }

    @Test
    public void EnderecoDtoToObjeto() {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setRua("rua 1");
        enderecoDTO.setNumero("10");
        enderecoDTO.setTipoResidencia(TipoResidencia.CASA);
        enderecoDTO.setCep("08940000");
        enderecoDTO.setLongradouro(Longradouro.RUA);
        enderecoDTO.setBairro("Jardim");
        CidadeDTO cidadeDTO = new CidadeDTO();
        cidadeDTO.setNome("Biritiba Mirim");
        cidadeDTO.setEstado(Estado.SAO_PAULO);
        enderecoDTO.setCidade(cidadeDTO);

        Endereco endereco = EnderecoDTO.dtoToObjeto(enderecoDTO);

        assertEquals(endereco.getCep(), enderecoDTO.getCep());
        assertEquals(endereco.getCidade().getNome(), enderecoDTO.getCidade().getNome());
        assertEquals(endereco.getCidade().getEstado(), enderecoDTO.getCidade().getEstado());
    }

    //Cartao
    @Test
    public void CartaoObjetoToDto() {
        Cartao cartao = new Cartao();
        cartao.setNumero("370092099936063");
        cartao.setCvv("2455");
        cartao.setBandeira(Bandeira.AMARICANEXPRESS);
        cartao.setNome("Cartao 1");


        CartaoDTO cartaoDTO = CartaoDTO.objetoToDto(cartao);

        assertEquals(cartaoDTO.getNome(), cartao.getNome());
        assertEquals(cartaoDTO.getCvv(), cartao.getCvv());
        assertEquals(cartaoDTO.getNumero(), cartao.getNumero());
        assertEquals(cartaoDTO.getBandeira(), cartao.getBandeira());
        assertEquals(cartaoDTO.getId(), cartao.getId());
    }

    @Test
    public void CartaoDtoToObjeto() {
        CartaoDTO cartaoDTO = new CartaoDTO();
        cartaoDTO.setNumero("370092099936063");
        cartaoDTO.setCvv("2455");
        cartaoDTO.setBandeira(Bandeira.AMARICANEXPRESS);
        cartaoDTO.setNome("Cartao 1");

        Cartao cartao = CartaoDTO.dtoToObjeto(cartaoDTO);

        assertEquals(cartaoDTO.getNome(), cartao.getNome());
        assertEquals(cartaoDTO.getCvv(), cartao.getCvv());
        assertEquals(cartaoDTO.getNumero(), cartao.getNumero());
        assertEquals(cartaoDTO.getBandeira(), cartao.getBandeira());
    }

    //Cliente
    @Test
    public void ClienteToDto() {
        Cliente cliente = genCliente();

        ClienteDTO clienteDTO = ClienteDTO.objetoToDto(cliente);

        assertEquals(clienteDTO.getCartoes().get(0).getNumero(), clienteDTO.getCartoes().get(0).getNumero());
        assertEquals(clienteDTO.getDataNascimentoDto(), cliente.getDataNascimento());
    }

    @Test
    public void ClienteDtoToCliente() {
        ClienteDTO clienteDTO = ClienteDTO.objetoToDto(genCliente());

        Cliente cliente = ClienteDTO.dtoToObjeto(clienteDTO);

        assertEquals(clienteDTO.getCartoes().get(0).getNumero(), clienteDTO.getCartoes().get(0).getNumero());
        assertEquals(clienteDTO.getDataNascimentoDto(), cliente.getDataNascimento());
    }

    private Cliente genCliente() {
        Cliente cliente = new Cliente();
        cliente.setTipoCliente(TipoCliente.FISICA);
        cliente.setDataNascimento(LocalDate.of(2000, 7, 26));
        cliente.setRank(55);
        List<Documento> documentoList = new ArrayList<>();
        Documento documento = new Documento();
        documento.setTipoDocumento(TipoDocumento.CPF);
        documento.setCodigo("24360025840");
        documentoList.add(documento);
        cliente.setGenero(Genero.MASCULINO);
        Telefone telefone = new Telefone();

        telefone.setDdd("011");
        telefone.setNumero("985372928");
        telefone.setTipoTelefone(TipoTelefone.MOVEL);

        cliente.setTelefone(telefone);

        Usuario usuario = new Usuario();

        usuario.setTipoUsuario(TipoUsuario.CLIENTE);
        usuario.setLogin("teste@gmail.com");
        usuario.setSenha("1234aaBB$");
        usuario.setNome("Cliente teste");

        cliente.setUsuario(usuario);

        List<Endereco> enderecoList = new ArrayList<>();
        Endereco endereco = new Endereco();

        endereco.setCep("08940000");
        endereco.setRua("rua 1");
        endereco.setBairro("Mogilar");
        endereco.setLongradouro(Longradouro.JARDIM);
        endereco.setTipoResidencia(TipoResidencia.CASA);

        Cidade cidade = new Cidade();
        cidade.setNome("Mogi das Cruzes");

        cidade.setEstado(Estado.SAO_PAULO);

        endereco.setCidade(cidade);
        endereco.setNumero("10");
        enderecoList.add(endereco);

        Endereco endereco1 = new Endereco();

        endereco1.setCep("04563254");
        endereco1.setRua("rua 2");
        endereco1.setBairro("Centro");
        endereco1.setLongradouro(Longradouro.JARDIM);
        endereco1.setTipoResidencia(TipoResidencia.CASA);

        Cidade cidade1 = new Cidade();
        cidade1.setNome("Guaruja");

        cidade1.setEstado(Estado.SAO_PAULO);

        endereco1.setCidade(cidade1);
        endereco1.setNumero("55");
        enderecoList.add(endereco1);

        cliente.setEnderecos(enderecoList);

        Documento documento2 = new Documento();
        documento2.setTipoDocumento(TipoDocumento.RG);
        documento2.setCodigo("535886263");
        documentoList.add(documento2);
        cliente.setDocumentos(documentoList);

        List<Cartao> cartaoList = new ArrayList<>();

        Cartao c1 = new Cartao();
        c1.setBandeira(Bandeira.AMARICANEXPRESS);
        c1.setNome("Cliente American Express Cartao");
        c1.setNumero("345108459964008");
        c1.setCvv("9285");
        Cartao c2 = new Cartao();
        c2.setBandeira(Bandeira.MASTERCARD);
        c2.setNome("Cliente 1 cartao");
        c2.setNumero("5458072591767327");
        c2.setCvv("167");

        cartaoList.add(c1);
        cartaoList.add(c2);

        cliente.setCartoes(cartaoList);

        List<Cupom> cupomList = new ArrayList<>();
        Cupom cupom1 = new Cupom();
        cupom1.setValor(10f);
        cupom1.setTipoCupom(TipoCupom.TROCA);
        cupom1.setCodigo("TROCA1");

        Cupom cupom2 = new Cupom();
        cupom2.setValor(70f);
        cupom2.setTipoCupom(TipoCupom.PROMOCIONAL);
        cupom2.setCodigo("PROMO2");

        cupomList.add(cupom1);
        cupomList.add(cupom2);

        cliente.setCupoms(cupomList);

        return cliente;
    }

    //Compra
    @Test
    public void CompraToDto() {
        Compra compra=genCompra();

        CompraDTO compraDTO=CompraDTO.objetoToDto(compra);

        assertEquals(compra.getFrete().getValor(), compraDTO.getFrete().getValor());
    }
    @Test
    public void CompraDtoToCompra() {
        CompraDTO compraDTO=CompraDTO.objetoToDto(genCompra());

        Compra compra=CompraDTO.dtoToObjeto(compraDTO);

    }

    private Compra genCompra() {

        Compra compra=new Compra();
        compra.setProdutos(genProdutos());

        Frete frete=new Frete();
        Cliente cliente=genCliente();
        frete.setEndereco(cliente.getEnderecos().get(0));
        frete.setValor(15F);
        compra.setFrete(frete);

        Pagamento pagament=new Pagamento();
        pagament.setCartao(cliente.getCartoes().get(0));
        pagament.setValor(11515-10f);
        List<Pagamento> pagamentoList=new ArrayList<>();
        pagamentoList.add(pagament);
        compra.setPagamentos(pagamentoList);

        List<Cupom> cupomList=new ArrayList<>();
        cupomList.add(cliente.getCupoms().get(0));
        compra.setCupoms(cupomList);

        compra.setCliente(cliente);

        compra.setStatus(Status.EM_PROCESSAMENTO);

        return compra;
    }

    private List<Produto> genProdutos() {
            List<Produto> produtoList=new ArrayList<>();
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

            Imagem imagem=new Imagem();
            imagem.setLink("https://www.notebookcheck.info/uploads/tx_nbc2/LenovoIdeapad330-15IKB__1_.JPG");

            Imagem imagem1=new Imagem();
            imagem1.setLink("https://www.notebookcheck.info/uploads/tx_nbc2/LenovoIdeapad330-15IKB__3_.JPG");

            Imagem imagem2=new Imagem();
            imagem2.setLink("https://www.notebookcheck.info/uploads/tx_nbc2/LenovoIdeapad330-15IKB__4_.JPG");

            Imagem imagem3=new Imagem();
            imagem3.setLink("https://www.notebookcheck.info/uploads/tx_nbc2/LenovoIdeapad330-15IKB__6_.JPG");
            produto.addImagem(imagem);
            produto.addImagem(imagem1);
            produto.addImagem(imagem2);
            produto.addImagem(imagem3);

            produto.setNotebook(notebook);


            Notebook avell = new Notebook();
            avell.setCodigo("293877");
            avell.setMarca("AVELL");
            avell.setModelo("C62 RTX LIV");

            avell.setSo(SO.WINDOWS10PRO);

            Tela tela1 = new Tela();
            tela1.setTamanho("17,3");
            tela1.setClock("144");

            avell.setCategoria(Categoria.PROFISSIONAL);
            CPU cpu1 = new CPU();
            cpu1.setModelo("i7");
            avell.setCpu(cpu1);
            GPU gpu1=new GPU();
            gpu1.setModelo("NVIDIA® GeForce® RTX 2060");
            avell.setGpu(gpu1);
            avell.setTela(tela1);

            List<RAM> rams=new ArrayList<>();
            RAM ram1 =new RAM();
            ram1.setClock("2666");
            ram1.setMemoria(8);
            rams.add(ram1);

            RAM ram2 =new RAM();
            ram2.setClock("2666");
            ram2.setMemoria(8);
            rams.add(ram2);

            avell.setRamList(rams);

            List<Armazenamento> armazenamentos=new ArrayList<>();
            Armazenamento armazenamento1 = new Armazenamento();
            armazenamento1.setMemoria(1000);
            armazenamento1.setTipoArmazenamento(TipoArmazenamento.HD);

            Armazenamento armazenamento2 = new Armazenamento();
            armazenamento2.setMemoria(500);
            armazenamento2.setTipoArmazenamento(TipoArmazenamento.SSD);


            armazenamentos.add(armazenamento2);
            armazenamentos.add(armazenamento1);

            avell.setArmazenamentoList(armazenamentos);

            Produto produto1=new Produto();
            produto1.setEstoque(7);
            produto1.setCusto(8000F);
            produto1.setPontuacaoCliente(75);


            produto1.setPrecificacao(precificacao);

            Imagem imagem4=new Imagem();
            imagem4.setLink("https://images.avell.com.br/media/catalog/product/cache/1/thumbnail/800x600/9df78eab33525d08d6e5fb8d27136e95/c/6/c62_10_.jpg");

            Imagem imagem5=new Imagem();
            imagem5.setLink("https://images.avell.com.br/media/catalog/product/cache/1/thumbnail/800x600/9df78eab33525d08d6e5fb8d27136e95/c/6/c62_12_.jpg");

            Imagem imagem6=new Imagem();
            imagem6.setLink("https://images.avell.com.br/media/catalog/product/cache/1/thumbnail/800x600/9df78eab33525d08d6e5fb8d27136e95/c/6/c62_7_.jpg");

            Imagem imagem7=new Imagem();
            imagem7.setLink("https://images.avell.com.br/media/catalog/product/cache/1/thumbnail/800x600/9df78eab33525d08d6e5fb8d27136e95/c/6/c62_5_.jpg");
            produto1.addImagem(imagem4);
            produto1.addImagem(imagem5);
            produto1.addImagem(imagem6);
            produto1.addImagem(imagem7);

            produto1.setNotebook(avell);

            produtoList.add(produto);
            produtoList.add(produto1);

            return produtoList;
    }

    //Cupom
    @Test
    public void CupomToDto(){
        Cupom cupom=new Cupom();
        cupom.setCodigo("PROMO1");
        cupom.setTipoCupom(TipoCupom.PROMOCIONAL);
        cupom.setValor(50f);

        CupomDTO cupomDTO=CupomDTO.objetoToDto(cupom);

        assertEquals(cupomDTO.getCodigo(), cupom.getCodigo());
    }
    @Test
    public void CupomDtoToCupom(){
        Cupom cupom=new Cupom();
        cupom.setCodigo("PROMO1");
        cupom.setTipoCupom(TipoCupom.PROMOCIONAL);
        cupom.setValor(50f);
        CupomDTO cupomDTO=CupomDTO.objetoToDto(cupom);

        Cupom cupom1=CupomDTO.dtoToObjeto(cupomDTO);

        assertEquals(cupomDTO.getCodigo(), cupom1.getCodigo());
    }

    //Notebook
    @Test
    public void NotebookToDto(){
        Notebook notebook=genProdutos().get(1).getNotebook();

        NotebookDTO notebookDTO=NotebookDTO.objetoToDto(notebook);

    }
    @Test
    public void NotebookDtoToNotebook(){
        NotebookDTO notebookDTO=NotebookDTO.objetoToDto(genProdutos().get(1).getNotebook());

        Notebook notebook=NotebookDTO.dtoToObjeto(notebookDTO);

    }

    //Produto
    @Test
    public void ProdutoToDto(){
        Produto produto=genProdutos().get(1);

        ProdutoDTO produtoDTO=ProdutoDTO.objetoToDto(produto);

        assertEquals(produtoDTO.getCusto(), produto.getCusto());
    }
    @Test
    public void ProdutoDtoToProduto(){
        ProdutoDTO produtoDTO=ProdutoDTO.objetoToDto(genProdutos().get(1));

        Produto produto=ProdutoDTO.dtoToObjeto(produtoDTO);

        assertEquals(produtoDTO.getCusto(), produto.getCusto());
    }

}
