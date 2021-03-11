package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.persistencia;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * author LucasDonizeti
 */
@Repository
public interface ClienteDAO extends JpaRepository<Cliente, Long> {

}