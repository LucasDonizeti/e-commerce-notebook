package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.persistencia;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.Cupom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * author LucasDonizeti
 */
@Repository
public interface CupomDAO extends JpaRepository<Cupom, UUID> {
    List<Cupom> findByClienteId(UUID hash);
}
