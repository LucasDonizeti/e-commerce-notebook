package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.log.persistencia;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.log.LogVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * author LucasDonizeti
 */

@Repository
public interface LogVendaDAO extends JpaRepository<LogVenda, UUID> {
    @Query(value = "select * from _log_venda where data between :inicio and :fim", nativeQuery = true)
    public List<LogVenda> findVendasByData(@Param("inicio") String inicio, @Param("fim")String fim);

}
