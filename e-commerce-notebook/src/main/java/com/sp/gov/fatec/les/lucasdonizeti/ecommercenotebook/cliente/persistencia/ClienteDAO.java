package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.persistencia;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

/**
 * author LucasDonizeti
 */
@Repository
public interface ClienteDAO extends JpaRepository<Cliente, UUID> {
    Optional<Cliente> findByUsuarioId(UUID id);
    Optional<Cliente> findByComprasId(UUID id);
}