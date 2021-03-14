package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.telefone.validacao;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.validacao.EmailValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * author LucasDonizeti
 */
public class NumeroTelefoneValidacao implements ConstraintValidator<NumeroTelefoneValidador, String> {
    private String message;

    @Override
    public void initialize(NumeroTelefoneValidador constraintAnnotation) {
        this.message= constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String numero, ConstraintValidatorContext constraintValidatorContext) {
        if (numero==null)
            return false;


        if(!Pattern.compile("^[0-9]*$").matcher(numero).find() || numero.length() != 9){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
