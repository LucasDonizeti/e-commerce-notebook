package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.estatistica.service;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.estatistica.ChartJs;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.estatistica.ChartJsDataset;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.estatistica.DataDatasetDTO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.estatistica.dao.EstatisticaDAO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.log.persistencia.LogVendaDAO;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.Produto;
import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.produto.servico.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * author LucasDonizeti
 */

@Service
public class ChartJsService {
    @Autowired
    private LogVendaDAO logVendaDAO;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private EstatisticaDAO estatisticaDAO;

    private List<ChartJsDataset> findAllDataset(String inicio, String fim){
        List<Produto> produtos=produtoService.findAll();
        List<ChartJsDataset> chartJsDatasets = new ArrayList<>();
        produtos.forEach(p->{
            ChartJsDataset chartJsDataset=new ChartJsDataset();
            chartJsDataset.setLabel(p.getNotebook().getMarca() + " " + p.getNotebook().getModelo());
            List<DataDatasetDTO> dataDatasetDTOS = null;
            try {
                dataDatasetDTOS = estatisticaDAO.findVendasByDataAndProduto(inicio, fim, p.getId());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            List<Integer> data=new ArrayList<>();
            dataDatasetDTOS.forEach(d->{
                data.add(d.getData());
            });
            chartJsDataset.setData(data);
            chartJsDatasets.add(chartJsDataset);
        });

        return chartJsDatasets;
    };

    public ChartJs findAllData(String inicio, String fim){
        ChartJs chartJs=new ChartJs();
        chartJs.setDatasets(findAllDataset(inicio, fim));
        chartJs.setLabels(findAllLabel(inicio, fim));
        return chartJs;
    }

    private List<String> findAllLabel(String inicio, String fim) {
        return estatisticaDAO.findAllDataSet(inicio, fim);
    }
}
