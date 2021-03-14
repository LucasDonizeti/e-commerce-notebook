package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.telefone.validacao;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * author LucasDonizeti
 */

@Constraint(validatedBy = DDDTelefoneValidacao.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface DDDTelefoneValidador {
    String message() default "DDD deve conter 3 numeros ex:'011'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
