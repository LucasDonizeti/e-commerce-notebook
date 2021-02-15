package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.controle;


import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.Cliente;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.TipoCliente;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.dto.ClienteDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.servico.ClienteServico;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento.Documento;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.documento.TipoDocumento;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.Cidade;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.Endereco;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.Estado;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.endereco.TipoEndereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * author LucasDonizeti
 */

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    private final ClienteServico clienteServico;

    @Autowired
    public ClienteController(ClienteServico clienteServico) {
        this.clienteServico = clienteServico;
    }

    @GetMapping
    public ResponseEntity<?> get() {
        return new ResponseEntity<>(clienteServico.findAll(), HttpStatus.OK);
    }

}
