package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.service;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.RAM;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.persistencia.RAMDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author LucasDonizeti
 */

@Service
public class RAMService {
    private final RAMDAO ramdao;

    @Autowired
    public RAMService(RAMDAO ramdao) {
        this.ramdao = ramdao;
    }

    public void delete(RAM ram){
        ramdao.delete(ram);
    }
}
