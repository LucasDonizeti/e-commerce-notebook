package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.persistencia;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * author LucasDonizeti
 */
@Repository
public interface CompraDAO extends JpaRepository<Compra, UUID> {
    List<Compra> findCompraByClienteId(UUID id);

    List<Compra> findByHabilitadoTrue();

    List<Compra> findCompraByClienteIdAndHabilitadoTrue(UUID id);
}
