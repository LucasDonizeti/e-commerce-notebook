package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.persistencia;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * author LucasDonizeti
 */

@Repository
public interface EnderecoDAO extends JpaRepository<Endereco, UUID> {
}
