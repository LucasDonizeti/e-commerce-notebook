package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.dto;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.Cliente;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.dto.ClienteDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Compra;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Frete;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Pagamento;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Status;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.Cupom;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.dto.CupomDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Produto;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.dto.ProdutoDTO;
import lombok.Getter;
import lombok.Setter;
import org.dozer.DozerBeanMapperBuilder;

import javax.persistence.*;
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
public class CompraDTO {
    private UUID id;

    @Size(min = 1)
    public List<ProdutoDTO> produtos = new ArrayList<>();

    @NotNull
    public FreteDTO frete;

    @NotNull
    public ClienteDTO cliente;

    @NotNull
    private Status status;

    @NotNull
    @Size(min = 1)
    private List<PagamentoDTO> pagamentos = new ArrayList<>();

    @NotNull
    @Size(min = 1)
    private List<CupomDTO> cupoms = new ArrayList<>();

    public static CompraDTO objetoToDto(Compra compra) {
        return DozerBeanMapperBuilder.buildDefault().map(compra, CompraDTO.class);
    }

    public static Compra dtoToObjeto(CompraDTO compraDTO) {
        Compra compra = DozerBeanMapperBuilder.buildDefault().map(compraDTO, Compra.class);
        return compra;
    }
}
