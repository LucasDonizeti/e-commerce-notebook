package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.dto;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.Armazenamento;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.Notebook;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.RAM;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.dto.ArmazenamentoDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.dto.NotebookDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Imagem;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Produto;
import lombok.Getter;
import lombok.Setter;
import org.dozer.DozerBeanMapperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * author LucasDonizeti
 */

@Getter
@Setter
public class ProdutoDTO {
    private UUID id;
    @NotNull
    private Float custo;
    @NotNull
    private int estoque;
    @NotNull
    private int pontuacaoCliente;
    @Size(min = 1, message = "insira pelo menos um imagem")
    private List<ImagemDTO> imagemList=new ArrayList<>();
    @NotNull @Valid
    private NotebookDTO notebook;
    @NotNull @Valid
    private PrecificacaoDTO precificacao;

    public Float getPrecoDeVenda(){
        if (precificacao != null)
            return custo + custo*precificacao.getMargemDeLucro();
        return custo;
    }


    public void addImagem(ImagemDTO imagem) {
        this.imagemList.add(imagem);
    }

    public void rmImagem(int indice){
        this.imagemList.remove(indice);
    }

    public void addEmptyImagem(){
        ImagemDTO imagemDTO=new ImagemDTO();
        this.imagemList.add(imagemDTO);
    }

    public static Produto dtoToObjeto(ProdutoDTO dto){
        Produto objeto = DozerBeanMapperBuilder.buildDefault().map(dto, Produto.class);
        return objeto;
    }

    public static ProdutoDTO objetoToDto(Produto objeto) {
        return DozerBeanMapperBuilder.buildDefault().map(objeto, ProdutoDTO.class);
    }
}
