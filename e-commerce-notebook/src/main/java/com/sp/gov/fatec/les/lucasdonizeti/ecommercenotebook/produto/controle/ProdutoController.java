package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.controle;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.dto.ClienteDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.*;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.dto.*;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Imagem;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Precificacao;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Produto;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.dto.ImagemDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.dto.PrecificacaoDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.dto.ProdutoDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.servico.ProdutoService;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * author LucasDonizeti
 */

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ModelAndView inicial(RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView("/produto/index.html");
        mv.addObject("produtos", produtoService.findAll());
        return mv;
    }

    @GetMapping("/teste")
    public ResponseEntity<?> testeinicial() {
        return new ResponseEntity<>(produtoService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/detalhes/{hash}")
    public ModelAndView detalhes(@PathVariable("hash") UUID hash) {
        ModelAndView mv = new ModelAndView("/produto/detalhes.html");
        Produto produto = produtoService.findAll().get(0);

        mv.addObject("produto", produto);
        return mv;
    }

    @GetMapping("/testeDTO")
    public ProdutoDTO testeDTO(){
        return genProduto2();
    }

    @GetMapping("/cadastro")
    public ModelAndView cadastro(){
        ModelAndView mv=new ModelAndView("/produto/cadastro.html");
        mv.addObject("produto", new ProdutoDTO());
        return mv;
    }

    @PostMapping("/cadastro")
    public ModelAndView cadastroPost(@Valid @ModelAttribute("produto")ProdutoDTO produtoDTO,
                                     BindingResult erros,
                                     @ModelAttribute("removeImagem") Optional<String> removeImagem,
                                     @ModelAttribute("removeArmazenamento") Optional<String> removeArmazenamento,
                                     @ModelAttribute("removeRam") Optional<String> removeRam,
                                     @ModelAttribute("add") Optional<String> add){

        if (removeImagem.isPresent())
            if (!removeImagem.get().equals("") && removeImagem.get()!=null) {
                produtoDTO.rmImagem(Integer.parseInt(removeImagem.get()));
                ModelAndView mv = new ModelAndView("/produto/cadastro.html");
                mv.addObject("produto", produtoDTO);
                mv.addObject("erros", erros.getAllErrors());
                return mv;
            }
        if (removeArmazenamento.isPresent())
            if (!removeArmazenamento.get().equals("") && removeArmazenamento.get()!=null) {
                produtoDTO.getNotebook().rmArmazenamento(Integer.parseInt(removeArmazenamento.get()));
                ModelAndView mv = new ModelAndView("/produto/cadastro.html");
                mv.addObject("produto", produtoDTO);
                mv.addObject("erros", erros.getAllErrors());
                return mv;
            }
        if (removeRam.isPresent())
            if (!removeRam.get().equals("") && removeRam.get()!=null) {
                produtoDTO.getNotebook().rmRam(Integer.parseInt(removeRam.get()));
                ModelAndView mv = new ModelAndView("/produto/cadastro.html");
                mv.addObject("produto", produtoDTO);
                mv.addObject("erros", erros.getAllErrors());
                return mv;
            }

        if (add.isPresent())
        if(!add.get().equals("") && add.get()!=null){
            switch (add.get()) {
                case "armazenamento":
                    produtoDTO.getNotebook().addEmptyArmazenamento();
                    break;
                case "ram":
                    produtoDTO.getNotebook().addEmptyRam();
                    break;
                case "imagem":
                    produtoDTO.addEmptyImagem();
                    break;
            }
            ModelAndView mv = new ModelAndView("/produto/cadastro.html");
            mv.addObject("produto", produtoDTO);
            mv.addObject("erros", erros.getAllErrors());
            return mv;
        }

        if (erros.hasErrors()){
            ModelAndView mv = new ModelAndView("/produto/cadastro.html");
            mv.addObject("produto", produtoDTO);
            mv.addObject("erros", erros.getAllErrors());
            return mv;
        }
        if (produtoDTO.getHash()== null) {
            ModelAndView mv = new ModelAndView("redirect:/adm/produtos");
            return mv;
        }else {
            ModelAndView mv = new ModelAndView("redirect:/adm/detalheProduto");
            return mv;
        }
    }

    private List<ProdutoDTO> genProdutoDTO(){
        List<ProdutoDTO> produtoList=new ArrayList<>();
        NotebookDTO notebook = new NotebookDTO();
        notebook.setCodigo("81FE0002BR");
        notebook.setMarca("Lenovo");
        notebook.setModelo("Ideapad 330");

        notebook.setSo(SO.WINDOWS10HOME);

        TelaDTO tela = new TelaDTO();
        tela.setTamanho("15,6");
        tela.setClock("60");

        notebook.setCategoria(Categoria.ENTRADA);
        CPUDTO cpu = new CPUDTO();
        cpu.setModelo("i5");
        notebook.setCpu(cpu);
        GPUDTO gpu=new GPUDTO();
        gpu.setModelo("UHD graphics 620");
        notebook.setGpu(gpu);
        notebook.setTela(tela);

        List<RAMDTO> ramList=new ArrayList<>();
        RAMDTO ram =new RAMDTO();
        ram.setClock("2666");
        ram.setMemoria(8);
        ramList.add(ram);
        notebook.setRamList(ramList);

        List<ArmazenamentoDTO> armazenamentoList=new ArrayList<>();
        ArmazenamentoDTO armazenamento = new ArmazenamentoDTO();
        armazenamento.setMemoria(1000);
        armazenamento.setTipoArmazenamento(TipoArmazenamento.HD);
        armazenamentoList.add(armazenamento);

        notebook.setArmazenamentoList(armazenamentoList);

        ProdutoDTO produto=new ProdutoDTO();
        produto.setEstoque(10);
        produto.setCusto(2000F);
        produto.setPontuacaoCliente(25);

        PrecificacaoDTO precificacao=new PrecificacaoDTO();

        precificacao.setNome("Baixa margem de lucro");
        precificacao.setMargemDeLucro(0.15F);
        produto.setPrecificacao(precificacao);

        ImagemDTO imagem=new ImagemDTO();
        imagem.setLink("https://www.notebookcheck.info/uploads/tx_nbc2/LenovoIdeapad330-15IKB__1_.JPG");

        ImagemDTO imagem1=new ImagemDTO();
        imagem1.setLink("https://www.notebookcheck.info/uploads/tx_nbc2/LenovoIdeapad330-15IKB__3_.JPG");

        ImagemDTO imagem2=new ImagemDTO();
        imagem2.setLink("https://www.notebookcheck.info/uploads/tx_nbc2/LenovoIdeapad330-15IKB__4_.JPG");

        ImagemDTO imagem3=new ImagemDTO();
        imagem3.setLink("https://www.notebookcheck.info/uploads/tx_nbc2/LenovoIdeapad330-15IKB__6_.JPG");
        produto.addImagem(imagem);
        produto.addImagem(imagem1);
        produto.addImagem(imagem2);
        produto.addImagem(imagem3);

        produto.setNotebook(notebook);


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

        produto1.setNotebook(avell);

        produtoList.add(produto);
        produtoList.add(produto1);

        return produtoList;
    }

    private ProdutoDTO genProduto2(){
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
