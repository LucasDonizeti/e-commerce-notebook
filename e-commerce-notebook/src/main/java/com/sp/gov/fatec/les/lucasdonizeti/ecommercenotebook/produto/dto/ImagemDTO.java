package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * author LucasDonizeti
 */
@Getter
@Setter
public class ImagemDTO {
    private UUID hash;
    private String link;
}
