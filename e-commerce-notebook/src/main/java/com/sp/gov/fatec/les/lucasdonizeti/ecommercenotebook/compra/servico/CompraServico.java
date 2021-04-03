package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.servico;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao.dto.CartaoDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao.servico.CartaoSarvice;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.Cliente;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.servico.ClienteServico;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Compra;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Status;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.persistencia.CompraDAO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.servico.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * author LucasDonizeti
 */
@Service
public class CompraServico {
    private final CompraDAO compraDAO;
    private final ProdutoService produtoService;
    private final ClienteServico clienteServico;
    private final CartaoSarvice cartaoSarvice;

    @Autowired
    public CompraServico(CompraDAO compraDAO, ProdutoService produtoService, ClienteServico clienteServico, CartaoSarvice cartaoSarvice) {
        this.compraDAO = compraDAO;
        this.produtoService = produtoService;
        this.clienteServico = clienteServico;
        this.cartaoSarvice = cartaoSarvice;
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
        Compra compra1 = compraDAO.save(compra);
        return findById(compra1.getId()).get();
    }

    public Compra setStatus(Compra compra, Status status) {
        Optional<Compra> compra1 = findById(compra.getId());
        if (compra1.isPresent()) {
            compra = compra1.get();
            compra.setStatus(status);
            return compraDAO.save(compra);
        }
        return null;
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


}
