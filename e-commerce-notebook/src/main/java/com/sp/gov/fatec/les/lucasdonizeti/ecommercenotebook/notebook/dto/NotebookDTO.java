package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.dto;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.Endereco;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.dto.EnderecoDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.*;
import lombok.Getter;
import lombok.Setter;
import org.dozer.DozerBeanMapperBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
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
public class NotebookDTO {
    private UUID hash;

    @NotBlank
    private String codigo;
    @NotBlank
    private String marca;
    @NotBlank
    private String modelo;
    @NotNull
    @Valid
    private TelaDTO tela;
    @NotNull
    @Valid
    private CPUDTO cpu;
    @NotNull
    @Valid
    private GPUDTO gpu;
    @NotNull
    private SO so;
    @NotNull
    private Categoria categoria;
    @Size(min = 1, message = "insira pelo menos uma mamória RAM")
    @Valid
    List<RAMDTO> ramList = new ArrayList();
    @Size(min = 1, message = "insira pelo menos uma mamória de armazenamento")
    @Valid
    List<ArmazenamentoDTO> armazenamentoList = new ArrayList();

    public void rmRam(int indice){
        this.ramList.remove(indice);
    }

    public void addEmptyRam(){
        RAMDTO ramdto=new RAMDTO();
        this.ramList.add(ramdto);
    }

    public void rmArmazenamento(int indice){
        this.armazenamentoList.remove(indice);
    }

    public void addEmptyArmazenamento(){
        ArmazenamentoDTO armazenamentoDTO=new ArmazenamentoDTO();
        this.armazenamentoList.add(armazenamentoDTO);
    }

    public static Notebook dtoToObjeto(NotebookDTO dto){
        Notebook objeto = DozerBeanMapperBuilder.buildDefault().map(dto, Notebook.class);

        if (objeto.getHash() == null)
            objeto.genHash();

        for(Armazenamento a : objeto.getArmazenamentoList())
            if (a.getHash() == null)
                a.genHash();

        if (objeto.getCpu().getHash() == null)
            objeto.getCpu().genHash();

        if (objeto.getGpu().getHash() == null)
            objeto.getGpu().genHash();

        for(RAM a : objeto.getRamList())
            if (a.getHash() == null)
                a.genHash();

        if (objeto.getTela().getHash() == null)
            objeto.getTela().genHash();

        return objeto;
    }

    public static NotebookDTO objetoToDto(Notebook objeto) {
        return DozerBeanMapperBuilder.buildDefault().map(objeto, NotebookDTO.class);
    }
}
