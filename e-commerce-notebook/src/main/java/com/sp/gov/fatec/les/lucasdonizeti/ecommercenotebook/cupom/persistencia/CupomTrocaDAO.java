package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.persistencia;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.CupomTroca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * author LucasDonizeti
 */

@Repository
public interface CupomTrocaDAO extends JpaRepository<CupomTroca, UUID> {
    List<CupomTroca> findByClienteId(UUID hash);

    List<CupomTroca> findByClienteIdAndHabilitadoFalse(UUID hash);

    List<CupomTroca> findByClienteIdAndHabilitadoTrue(UUID hash);
}
