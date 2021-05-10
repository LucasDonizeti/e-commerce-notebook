package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.validacao;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento.validacao.DocumentoValidacao;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * author LucasDonizeti
 */

@Constraint(validatedBy = CepValidacao.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface CepValidador {
    String message() default "CEP inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
