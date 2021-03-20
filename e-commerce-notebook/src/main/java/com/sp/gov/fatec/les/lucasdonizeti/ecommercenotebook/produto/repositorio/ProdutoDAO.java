package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.repositorio;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Produto;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * author LucasDonizeti
 */

@Repository
public interface ProdutoDAO extends JpaRepository<Produto, UUID> {

}
