package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.infrestructure.cep;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.Endereco;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.Estado;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * author LucasDonizeti
 */
@Getter
@Setter
public class CepAPIDTO {
    private String cep;
    private String uf;
    private String localidade;

    public static Endereco mergeEndereco(Endereco endereco, CepAPIDTO cepAPIDTO) {
        if (cepAPIDTO.getCep() != null)
            endereco.setCep(cepAPIDTO.getCep().replaceAll("-", ""));
        if (cepAPIDTO.getLocalidade() != null)
            endereco.getCidade().setNome(cepAPIDTO.getLocalidade());
        if (cepAPIDTO.getUf() != null) {
            List<Estado> estadoStream = Arrays.stream(Estado.values())
                    .filter(e -> e.getDescricao().equals(cepAPIDTO.getUf()))
                    .collect(Collectors.toList());
            if (estadoStream.get(0) != null)
                endereco.getCidade().setEstado(estadoStream.get(0));
        }
        return endereco;
    }
}
