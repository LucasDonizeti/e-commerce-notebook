package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.persistencia;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.compra.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * author LucasDonizeti
 */
@Repository
public interface ItemDAO extends JpaRepository<Item, UUID> {
}
