package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.dto;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.dto.ClienteDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Compra;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Status;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.dto.CupomPromocionalDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.dto.CupomTrocaDTO;
import lombok.Getter;
import lombok.Setter;
import org.dozer.DozerBeanMapperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

/**
 * author LucasDonizeti
 */
@Getter
@Setter
public class CompraDTO {
    private UUID id;

    private String data;

    @Size(min = 1)
    public List<ItemDTO> itens = new ArrayList<>();

    @NotNull
    @Valid
    public FreteDTO frete;

    @NotNull
    public ClienteDTO cliente;

    private Status status=Status.REPROVADA;

    @NotNull
    @Size(min = 1)
    private List<PagamentoDTO> pagamentos = new ArrayList<>();

    @NotNull
    private List<CupomTrocaDTO> cupomsDeTroca = new ArrayList<>();


    private CupomPromocionalDTO cupomPromocional;

    public float getTotal(){
        AtomicReference<Float> buff= new AtomicReference<>(0f);
        itens.forEach(i->{
            buff.updateAndGet(v -> v + i.getSubtotal());
        });

        return buff.get();
    }

    public Float getValorDeCompra(){
        float valorDeCompraFinal=0;
        for (ItemDTO i : itens){
            valorDeCompraFinal+=(i.getProduto().getPrecoDeVenda() * i.getQuantidade());
        }

        if (frete!=null)
            valorDeCompraFinal+=frete.getValor();

        return valorDeCompraFinal;
    }

    public Float getTotalPago(){
        Float totalPago=0f;

        for (PagamentoDTO p:pagamentos)
            if (p.getHabilitado() == true)
                totalPago+=p.getValor();

        for (CupomTrocaDTO c:cupomsDeTroca)
            if(c.getHabilitado())
                totalPago+=c.getValor();

        if (cupomPromocional!=null)
            if (cupomPromocional.getValor()!=null)
            totalPago+=cupomPromocional.getValor();

        return totalPago;
    }

    public void addProduto(ItemDTO item){
        itens.add(item);
    }

    public void setQuantidade(int indice, int quantidade){
        itens.get(indice).setQuantidade(quantidade);
    }

    public static CompraDTO objetoToDto(Compra compra) {
        CompraDTO compraDTO = DozerBeanMapperBuilder.buildDefault().map(compra, CompraDTO.class);
        compraDTO.setData(compra.getDataCriacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        return compraDTO;
    }

    public static Compra dtoToObjeto(CompraDTO compraDTO) {
        Compra compra = DozerBeanMapperBuilder.buildDefault().map(compraDTO, Compra.class);
        return compra;
    }

    public void excProduto(int i) {
        itens.remove(i);
    }
}
