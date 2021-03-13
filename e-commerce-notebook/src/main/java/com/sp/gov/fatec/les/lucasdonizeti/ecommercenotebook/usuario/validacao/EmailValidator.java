package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.validacao;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * author LucasDonizeti
 */

@Constraint(validatedBy = EmailValidacao.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailValidator {
    String message() default "Email invalido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
