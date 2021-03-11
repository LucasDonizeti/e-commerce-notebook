package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.controle;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.notebook.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * author LucasDonizeti
 */
@RestController
@RequestMapping("/notebook")
public class NotebookController {
    @GetMapping
    public ResponseEntity<?> teste() {
        Notebook notebook = new Notebook();
        notebook.setCodigo("81FE0002BR");
        notebook.setMarca("Lenovo");
        notebook.setModelo("Ideapad 330");

        notebook.setSo(SO.WINDOWS10HOME);

        Tela tela = new Tela();
        tela.setTamanho("15,6");
        tela.setClock("60");

        notebook.setCategoria(Categoria.ENTRADA);
        CPU cpu = new CPU();
        cpu.setModelo("i5");
        notebook.setCpu(cpu);
        GPU gpu=new GPU();
        gpu.setModelo("UHD graphics 620");
        notebook.setGpu(gpu);
        notebook.setTela(tela);

        List<RAM> ramList=new ArrayList<>();
        RAM ram =new RAM();
        ram.setClock("2666");
        ram.setMemoria(8);
        ramList.add(ram);
        notebook.setRamList(ramList);

        List<Armazenamento> armazenamentoList=new ArrayList<>();
        Armazenamento armazenamento = new Armazenamento();
        armazenamento.setMemoria(1000);
        armazenamento.setTipoArmazenamento(TipoArmazenamento.HD);
        armazenamentoList.add(armazenamento);

        notebook.setArmazenamentoList(armazenamentoList);

        return new ResponseEntity<>(notebook, HttpStatus.OK);
    }
}
