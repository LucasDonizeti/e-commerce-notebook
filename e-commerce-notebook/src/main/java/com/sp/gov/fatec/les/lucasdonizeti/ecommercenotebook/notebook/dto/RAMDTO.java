package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * author LucasDonizeti
 */
@Getter
@Setter
public class RAMDTO {
    @NotNull
    private int memoria;
    @NotBlank
    @Size(max = 8)
    private String clock;
}
