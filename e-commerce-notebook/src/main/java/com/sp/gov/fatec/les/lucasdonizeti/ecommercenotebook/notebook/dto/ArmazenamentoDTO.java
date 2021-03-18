package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.dto;


import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.TipoArmazenamento;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;


/**
 * author LucasDonizeti
 */
@Getter
@Setter
public class ArmazenamentoDTO {
    private UUID hash;
    @NotNull
    private int memoria;

    @NotNull
    private TipoArmazenamento tipoArmazenamento;
}
