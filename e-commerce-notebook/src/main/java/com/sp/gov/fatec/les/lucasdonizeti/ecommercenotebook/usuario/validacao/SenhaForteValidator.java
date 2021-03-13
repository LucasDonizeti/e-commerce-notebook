package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.validacao;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * author LucasDonizeti
 */

@Constraint(validatedBy = SenhaForteValidacao.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface SenhaForteValidator {
    String message() default "Senha fraca";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
