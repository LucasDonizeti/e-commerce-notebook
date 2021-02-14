package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.config;

import java.util.List;

/**
 * author LucasDonizeti
 */
public interface Servico<O, K>{
    O save(O object);
    void delete(K id);
    O put(O object);
    O findById(K id);
    List<O> findAll();
}
