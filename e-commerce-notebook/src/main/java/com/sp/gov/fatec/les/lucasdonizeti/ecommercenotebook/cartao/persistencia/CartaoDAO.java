package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao.persistencia;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cartao.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * author LucasDonizeti
 */
@Repository
public interface CartaoDAO extends JpaRepository<Cartao, UUID> {
}
