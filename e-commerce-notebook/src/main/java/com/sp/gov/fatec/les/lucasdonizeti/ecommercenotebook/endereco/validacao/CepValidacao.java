package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.validacao;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento.validacao.DocumentoValidador;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.infrestructure.cep.CepAPIDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.infrestructure.cep.CepClientAPI;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

/**
 * author LucasDonizeti
 */
public class CepValidacao implements ConstraintValidator<CepValidador, String> {
    private String message;

    @Override
    public void initialize(CepValidador constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String cep, ConstraintValidatorContext constraintValidatorContext) {
        if (cep.matches("\\d+") && cep.length() == 8) {
            Optional<CepAPIDTO> cepAPIDTO = CepClientAPI.findCepByViaCepAPI(cep);
            if (!cepAPIDTO.isEmpty()) {
                if (cepAPIDTO.get().getErro() == null) {
                    return true;
                } else if (cepAPIDTO.get().getErro()) {
                    constraintValidatorContext.disableDefaultConstraintViolation();
                    constraintValidatorContext.buildConstraintViolationWithTemplate("Esse CEP não existe")
                            .addConstraintViolation();
                    return false;
                }
            }
        } else {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation();
            return false;
        }
        System.err.println("Não foi possivel validar cep: " + cep);
        System.err.println("Cep '" + cep +"' foi considerado válido apesar da excessão.");
        return true;
    }
}
