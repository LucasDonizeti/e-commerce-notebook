package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.servico;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.*;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Imagem;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Precificacao;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Produto;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.repositorio.ProdutoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * author LucasDonizeti
 */
@Service
public class ProdutoService {
    private final ProdutoDAO produtoDAO;

    @Autowired
    public ProdutoService(ProdutoDAO produtoDAO) {
        this.produtoDAO = produtoDAO;
    }

    public List<Produto> findAll(){
        return genListProduto();
        //return produtoDAO.findAll();
    }




    private List<Produto> genListProduto(){
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
}
