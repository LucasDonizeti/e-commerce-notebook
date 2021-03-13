package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.repositorio;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * author LucasDonizeti
 */

@Repository
public interface ProdutoDAO extends JpaRepository<Produto, Long> {
}
