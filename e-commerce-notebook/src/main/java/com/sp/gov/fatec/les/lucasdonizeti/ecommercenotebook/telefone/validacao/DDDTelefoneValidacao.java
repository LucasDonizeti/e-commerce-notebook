package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.telefone.validacao;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * author LucasDonizeti
 */
public class DDDTelefoneValidacao implements ConstraintValidator<DDDTelefoneValidador, String> {
    private String message;

    @Override
    public void initialize(DDDTelefoneValidador constraintAnnotation) {
        this.message= constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String ddd, ConstraintValidatorContext constraintValidatorContext) {
        if (ddd==null)
            return false;

        if(!Pattern.compile("^[0-9]*$").matcher(ddd).find() || ddd.length() != 3){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
