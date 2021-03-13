package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.validacao;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * author LucasDonizeti
 */
public class EmailValidacao implements ConstraintValidator<EmailValidator, String> {
    private String message;

    @Override
    public void initialize(EmailValidator constraintAnnotation) {
        this.message= constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {

        String[] buffer=email.split("@");

        if (buffer.length!=2){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation();
            return false;
        }else{
            String[] host=buffer[1].split("\\.");
            if (host.length<2){
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate(message)
                        .addConstraintViolation();
                return false;
            }
        }
        return true;
    }
}
