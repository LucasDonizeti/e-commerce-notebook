package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.infrestructure.cep;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;


import java.util.Optional;

/**
 * author LucasDonizeti
 */
public class CepClientAPI {
    public static Optional<CepAPIDTO> findCepByViaCepAPI(String cep) {
        try {
            RestTemplate restTemplate = new RestTemplateBuilder().rootUri("https://viacep.com.br/ws").build();
            return Optional.ofNullable(restTemplate.getForObject("/{cep}/json", CepAPIDTO.class, cep));
        }catch (Exception e){
            return Optional.empty();
        }
    }
}
