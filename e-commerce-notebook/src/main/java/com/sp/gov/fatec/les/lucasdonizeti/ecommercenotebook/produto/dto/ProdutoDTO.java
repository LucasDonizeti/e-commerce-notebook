package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.dto;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.dto.ArmazenamentoDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.dto.NotebookDTO;
import lombok.Getter;
import lombok.Setter;

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
    private UUID hash;
    @NotNull
    private Float custo;
    @NotNull
    private int estoque;
    @NotNull
    private int pontuacaoCliente;
    @Size(min = 1, message = "insira pelo menos um imagem")
    private List<ImagemDTO> imagens=new ArrayList<>();
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
        this.imagens.add(imagem);
    }

    public void rmImagem(int indice){
        this.imagens.remove(indice);
    }

    public void addEmptyImagem(){
        ImagemDTO imagemDTO=new ImagemDTO();
        this.imagens.add(imagemDTO);
    }
}
