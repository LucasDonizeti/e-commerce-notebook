package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.service;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.Notebook;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.persistencia.NotebookDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author LucasDonizeti
 */

@Service
public class NotebookService {
    private final NotebookDAO notebookDAO;

    @Autowired
    public NotebookService(NotebookDAO notebookDAO) {
        this.notebookDAO = notebookDAO;
    }
    public Notebook save(Notebook notebook){
        return notebookDAO.save(notebook);
    }
}
