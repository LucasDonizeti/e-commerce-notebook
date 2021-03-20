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
public class TelaDTO {
    private UUID id;
    @NotBlank
    @Size(max=4)
    private String tamanho;
    @NotBlank
    @Size(max=3)
    private String clock;
}
