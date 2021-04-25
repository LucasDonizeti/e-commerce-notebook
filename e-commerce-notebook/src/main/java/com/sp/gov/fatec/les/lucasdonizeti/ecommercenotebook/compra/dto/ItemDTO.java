package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.dto;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Compra;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Item;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Status;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Produto;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.dto.ProdutoDTO;
import lombok.Getter;
import lombok.Setter;
import org.dozer.DozerBeanMapperBuilder;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * author LucasDonizeti
 */

@Getter
@Setter
public class ItemDTO {
    private UUID id;
    @NotNull
    private ProdutoDTO produto;

    @Min(value = 1)
    private int quantidade=1;

    private Status status;

    private CompraDTO compra;

    public float getSubtotal(){
        return quantidade * produto.getPrecoDeVenda();
    }

    public static ItemDTO objetoToDto(Item item) {
        return DozerBeanMapperBuilder.buildDefault().map(item, ItemDTO.class);
    }

    public static Item dtoToObjeto(ItemDTO itemDTO) {
        return DozerBeanMapperBuilder.buildDefault().map(itemDTO, Item.class);
    }

}
