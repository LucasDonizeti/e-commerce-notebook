package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.dto;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.dto.ClienteDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Compra;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Status;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.dto.CupomPromocionalDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.dto.CupomTrocaDTO;
import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.description.type.TypeList;
import org.dozer.DozerBeanMapperBuilder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    private Float valorDeCompra;

    private Float totalPago;

    @NotNull
    private ClienteDTO cliente;

    private Status status = Status.REPROVADA;

    private FreteDTO frete;

    @NotNull
    @Size(min = 1)
    private List<PagamentoDTO> pagamentos = new ArrayList<>();

    @NotNull
    private List<CupomTrocaDTO> cupomsDeTroca = new ArrayList<>();

    private CupomPromocionalDTO cupomPromocional;

    public void calcularValores() {
        calcTotalPago();
        calcItem();
        calcValorDeCompra();
        calcFrete();
    }

    private void calcFrete(){
        Float frete=0f;
        for (ItemDTO i:itens){
            if (i!=null)
                if (i.getFrete()!=null)
                    if (i.getFrete().getValor()!=null)
            frete+=i.getFrete().getValor() * i.getQuantidade();
        }
        if (this.frete!=null)
        this.frete.setValor(frete);
    }

    private void calcItem(){
        for(int x=0;x<itens.size();x++){
            itens.get(x).setPrecoDeVendaProdutos(itens.get(x).getProduto().getPrecoDeVenda());
        }
    }

    public Boolean temItemSemFrete() {
        Boolean result = false;
        for (ItemDTO i : itens) {
            if (i.getFrete() != null)
                if (i.getFrete().getValor() == null || i.getFrete().getValor() == 0)
                    result = true;
            if (i.getFrete() == null)
                result = true;
        }
        return result;
    }

    private void calcValorDeCompra() {
        float valorDeCompraFinal = 0;
        for (ItemDTO i : itens) {
            valorDeCompraFinal += i.getTotalItem();
        }
        valorDeCompra = valorDeCompraFinal;
    }

    private void calcTotalPago() {
        float totalPago = 0;
        for (PagamentoDTO p : pagamentos)
            if (p.getHabilitado()==true)
                totalPago += p.getValor();

        for (CupomTrocaDTO c : cupomsDeTroca)
            if (c.getHabilitado()==true)
                totalPago += c.getValor();

        if (cupomPromocional != null)
            totalPago += cupomPromocional.getValor();

        this.totalPago = totalPago;
    }

    public Float getTotalFrete() {
        float totalFrete = 0;
        for (ItemDTO i : itens) {
            if (i.getFrete() != null)
                if (i.getFrete().getValor() != null)
                    totalFrete += i.getFrete().getValor();
        }
        return totalFrete;
    }

    public Float getValorDeCompraSemFrete() {
        float x = 0;
        for (ItemDTO i : itens) {
            if (i.getFrete() != null)
                if (i.getFrete().getValor() != null)
                    x += i.getFrete().getValor() * i.getQuantidade();
        }
        return x;
    }

    public void addProduto(ItemDTO item) {
        itens.add(item);
    }

    public void setQuantidade(int indice, int quantidade) {
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
