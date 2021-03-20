package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.persistencia;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.Armazenamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * author LucasDonizeti
 */
@Repository
public interface ArmazenamentoDAO extends JpaRepository<Armazenamento, UUID> {
}
