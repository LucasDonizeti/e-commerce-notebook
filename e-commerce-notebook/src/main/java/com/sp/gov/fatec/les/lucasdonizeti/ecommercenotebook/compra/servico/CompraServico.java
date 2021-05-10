package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.servico;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao.servico.CartaoSarvice;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.Cliente;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.servico.ClienteServico;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Compra;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Item;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Status;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.persistencia.CompraDAO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.CupomPromocional;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.CupomTroca;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.servico.CupomPromocionalService;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.servico.CupomTrocaService;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Produto;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.servico.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * author LucasDonizeti
 */
@Service
public class CompraServico {
    private final CompraDAO compraDAO;
    private final ProdutoService produtoService;
    private final ClienteServico clienteServico;
    private final CartaoSarvice cartaoSarvice;
    private final CupomTrocaService cupomServico;
    private final CupomPromocionalService cupomPromocionalService;

    @Autowired
    public CompraServico(CompraDAO compraDAO, ProdutoService produtoService, ClienteServico clienteServico, CartaoSarvice cartaoSarvice, CupomTrocaService cupomServico, CupomPromocionalService cupomPromocionalService) {
        this.compraDAO = compraDAO;
        this.produtoService = produtoService;
        this.clienteServico = clienteServico;
        this.cartaoSarvice = cartaoSarvice;
        this.cupomServico = cupomServico;
        this.cupomPromocionalService = cupomPromocionalService;
    }

    public Optional<Compra> findById(UUID hash) {
        Optional<Compra> compraOptional = compraDAO.findById(hash);
        if (compraOptional.isPresent()) {
            Compra compra = compraOptional.get();
            compra.getItens().forEach(i -> {
                i.setProduto(produtoService.findById(i.getProduto().getId()).get());
            });
            compra.setCliente(clienteServico.findById(compra.getCliente().getId()).get());
            compra.getPagamentos().forEach(p -> {
                p.setCartao(cartaoSarvice.findById(p.getCartao().getId()).get());
            });
            return Optional.of(compra);
        }
        return compraOptional;
    }

    public Compra save(Compra compra) {
        return compraDAO.save(compra);
    }

    public Compra setStatusCompraItem(Compra compra, Status status) {
        Boolean itensMesmoStatus=true;
        for (int x=0;x<compra.getItens().size();x++)
            if (compra.getItens().get(x).getStatus() != compra.getStatus() && itensMesmoStatus)
                itensMesmoStatus=false;
        if (itensMesmoStatus) {
            compra.setStatus(status);
            for (int x=0;x<compra.getItens().size();x++)
                compra.getItens().get(x).setStatus(status);
            return compraDAO.save(compra);
        }
        return compra;
    }

    public Compra setStatusCompra(Compra compra, Status status) {
        Boolean itensMesmoStatus=true;
        for (int x=0;x<compra.getItens().size();x++)
            if (compra.getItens().get(x).getStatus() != compra.getStatus() && itensMesmoStatus)
                itensMesmoStatus=false;
        if (itensMesmoStatus) {
            compra.setStatus(status);
            return compraDAO.save(compra);
        }
        return compra;
    }

    public List<Compra> findAll() {
        return compraDAO.findAll();
    }

    public List<Compra> findHabilitado() {
        return compraDAO.findByHabilitadoTrue();
    }

    public List<Compra> findCompraByClienteId(UUID id) {
        return compraDAO.findCompraByClienteId(id);
    }

    public List<Compra> findCompraHabilitadoByClienteId(UUID id) {
        return compraDAO.findCompraByClienteIdAndHabilitadoTrue(id);
    }


    public Status nextAdm(Status status) {
        switch (status) {
            case APROVADA:
                return Status.EM_PROCESSAMENTO;
            case EM_PROCESSAMENTO:
                return Status.EM_TRANSITO;
            case EM_TRANSITO:
                return Status.ENTREGUE;
            case TROCA_AUTORIZADA:
                return Status.TROCA_CONCLUIDA;
            default:
                return status;
        }
    }

    public Status nextCli(Status status) {
        switch (status) {
            case ENTREGUE:
            case TROCA_NAO_AUTORIZADA:
                return Status.EM_TROCA;
            default:
                return status;
        }
    }

    public Status nextTrocaAdm(Status status, Boolean aceitarTroca) {
        if (status == Status.EM_TROCA)
            return (aceitarTroca ? Status.TROCA_AUTORIZADA : Status.TROCA_NAO_AUTORIZADA);
        return status;
    }

    public Status nextValidSystem(Status status) {
        if (status == Status.REPROVADA)
            return Status.APROVADA;
        return status;
    }

    public void concluirTroca(Compra compra) {
        compra = findById(compra.getId()).get();
        Cliente cliente = clienteServico.findById(compra.getCliente().getId()).get();
        CupomTroca cupom = new CupomTroca();
        cupom.setCodigo(gerarCodigoTroca());
        cupom.setValor(compra.getTotalPago());

        cupom.setCliente(cliente);
        cupomServico.save(cupom);
    }

    public void concluirTrocaItem(Compra compra, UUID item, Boolean reporEstoque) {
        for (int x = 0; x < compra.getItens().size(); x++) {
            if (compra.getItens().get(x).getId().equals(item)) {
                if (reporEstoque)
                    produtoService.adicionarEstoque(compra.getItens().get(x).getProduto().getId(), compra.getItens().get(x).getQuantidadeEmTroca());

                compra.getItens().get(x).setQuantidade(compra.getItens().get(x).getQuantidade() - compra.getItens().get(x).getQuantidadeEmTroca());
                compra.getItens().get(x).setQuantidadeEmTroca(0);
                if (compra.getItens().get(x).getQuantidade()>0)
                    compra.getItens().get(x).setStatus(Status.ENTREGUE);
                compraDAO.save(compra);

                CupomTroca cupom = new CupomTroca();
                cupom.setCodigo(gerarCodigoTroca());
                cupom.setValor(compra.getTotalPago());

                cupom.setCliente(clienteServico.findById(compra.getCliente().getId()).get());
                cupomServico.save(cupom);
            }
        }
    }

    private String gerarCodigoTroca() {
        LocalDate localDate = LocalDate.now();
        return ("TROCA" + (localDate.getDayOfMonth() < 10 ? "0" : "") + localDate.getDayOfMonth() + (localDate.getMonthValue() < 10 ? "0" : "") + localDate.getMonthValue() + localDate.getYear());
    }
}
