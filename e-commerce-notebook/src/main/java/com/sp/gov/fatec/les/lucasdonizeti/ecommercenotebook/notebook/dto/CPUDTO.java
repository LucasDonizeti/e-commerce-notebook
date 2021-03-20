package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * author LucasDonizeti
 */
@Getter
@Setter
public class CPUDTO {
    private UUID id;
    @NotBlank
    @Size(max = 50)
    private String modelo;
}
