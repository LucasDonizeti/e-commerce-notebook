package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.usuario.validacao;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * author LucasDonizeti
 */
public class SenhaForteValidacao implements ConstraintValidator<SenhaForteValidator, String> {
    private String message;

    @Override
    public void initialize(SenhaForteValidator constraintAnnotation) {
        this.message= constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String senha, ConstraintValidatorContext constraintValidatorContext) {
        if(senha == null)
            return false;
        if (senha.length()<8){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("senha possui menos de 8 caracteres!")
                    .addConstraintViolation();
            return false;
        }
        if(senha.equals(senha.toUpperCase()) || senha.equals(senha.toLowerCase())){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("senha deve ser composta de letras maiusculas e minusculas!")
                    .addConstraintViolation();
            return false;
        }
        if(!Pattern.compile("[$&+,:;=?@#|]").matcher(senha).find()){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("senha não possui caracteres especiais!")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
