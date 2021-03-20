package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.servico;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Imagem;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.repositorio.ImagemDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author LucasDonizeti
 */
@Service
public class ImagemService {
    private final ImagemDAO imagemDAO;

    @Autowired
    public ImagemService(ImagemDAO imagemDAO) {
        this.imagemDAO = imagemDAO;
    }

    public void delete(Imagem imagem){
        imagemDAO.delete(imagem);
    }
}
