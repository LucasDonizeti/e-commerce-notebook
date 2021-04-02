package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.persistencia;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * author LucasDonizeti
 */
@Repository
public interface ClienteDAO extends JpaRepository<Cliente, UUID> {
    Optional<Cliente> findByUsuarioId(UUID id);
}