package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.persistencia;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * author LucasDonizeti
 */
@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findById(UUID id);
}
