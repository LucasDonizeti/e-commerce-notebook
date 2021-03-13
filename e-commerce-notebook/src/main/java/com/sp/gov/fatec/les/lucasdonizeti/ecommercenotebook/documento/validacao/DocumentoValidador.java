package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento.validacao;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * author LucasDonizeti
 */

@Constraint(validatedBy = DocumentoValidacao.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface DocumentoValidador {
    String message() default "Email invalido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
