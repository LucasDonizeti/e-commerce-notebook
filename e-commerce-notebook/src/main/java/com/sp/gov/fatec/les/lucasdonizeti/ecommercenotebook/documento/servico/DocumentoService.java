package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento.servico;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento.Documento;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento.persistencia.DocumentoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author LucasDonizeti
 */
@Service
public class DocumentoService {
    private final DocumentoDAO documentoDAO;

    @Autowired
    public DocumentoService(DocumentoDAO documentoDAO) {
        this.documentoDAO = documentoDAO;
    }

    public void delete(Documento documento){
            documentoDAO.delete(documento);
    }

    public Boolean existsByCodigo(String codigo){
        return documentoDAO.existsByCodigo(codigo);
    }
}
