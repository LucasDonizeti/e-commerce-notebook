package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento.persistencia;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * author LucasDonizeti
 */
@Repository
public interface DocumentoDAO extends JpaRepository<Documento, UUID> {
    boolean existsByCodigo(String codigo);

    Optional<Documento> findById(UUID hash);
}
