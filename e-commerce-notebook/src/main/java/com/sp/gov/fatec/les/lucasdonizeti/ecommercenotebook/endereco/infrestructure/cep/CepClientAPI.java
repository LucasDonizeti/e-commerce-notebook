package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.infrestructure.cep;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

/**
 * author LucasDonizeti
 */
public class CepClientAPI {
    public static CepAPIDTO findCepByViaCepAPI(String cep) {
        RestTemplate restTemplate = new RestTemplateBuilder().rootUri("https://viacep.com.br/ws").build();
        return  restTemplate.getForObject("/{cep}/json", CepAPIDTO.class, cep);
    }
}
