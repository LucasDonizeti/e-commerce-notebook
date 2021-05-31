package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.log.controle;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.estatistica.service.ChartJsService;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.log.service.LogVendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * author LucasDonizeti
 */


@Controller
@RequestMapping("/estatistica")
public class LogVendaController {

    @Autowired
    private LogVendaService logVendaService;

    @Autowired
    private ChartJsService chartJsService;

    @GetMapping("/findVendasPorProdutoAndPeriodo")
    public ResponseEntity<?> findVendasPorProdutoAndPeriodo(@RequestParam String dataInicio,
                                                            @RequestParam String dataFim){
        return new ResponseEntity<>(chartJsService.findAllData(dataInicio, dataFim), HttpStatus.OK);
    }

    @GetMapping
    public ModelAndView estat(){
        return new ModelAndView("estatistica.html");
    }
}
