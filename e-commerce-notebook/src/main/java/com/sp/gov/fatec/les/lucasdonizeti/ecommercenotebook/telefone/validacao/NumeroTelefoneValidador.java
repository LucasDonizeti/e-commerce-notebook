package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.telefone.validacao;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * author LucasDonizeti
 */

@Constraint(validatedBy = NumeroTelefoneValidacao.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface NumeroTelefoneValidador {
    String message() default "Telefone deve conter 9 digitos";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
