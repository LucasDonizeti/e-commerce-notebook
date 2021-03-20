package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.repositorio;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Imagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * author LucasDonizeti
 */
@Repository
public interface ImagemDAO extends JpaRepository<Imagem, UUID> {
}
