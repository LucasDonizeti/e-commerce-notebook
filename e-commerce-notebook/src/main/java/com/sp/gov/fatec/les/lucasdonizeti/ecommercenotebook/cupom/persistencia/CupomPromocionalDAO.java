package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.persistencia;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cupom.CupomPromocional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * author LucasDonizeti
 */

@Repository
public interface CupomPromocionalDAO extends JpaRepository<CupomPromocional, UUID> {
    Optional<CupomPromocional> findByCodigo(String codigo);
}
