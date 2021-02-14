package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.controle;


import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.servico.ClienteServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
