package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.estatistica.dao;

import com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.estatistica.DataDatasetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * author LucasDonizeti
 */

@Repository
public class EstatisticaDAO {
    private String url;
    private String login;
    private String senha;

    @Autowired
    EstatisticaDAO(@Value("${spring.datasource.url}") String url,
                   @Value("${spring.datasource.username}") String login,
                   @Value("${spring.datasource.password}") String senha){
        this.url = url;
        this.login = login;
        this.senha = senha;
    }

    public List<DataDatasetDTO> findVendasByDataAndProduto(String inicio, String fim, UUID id) throws SQLException {
        try {
            Connection con = (Connection) DriverManager.getConnection(url, login, senha);
            Statement st = con.createStatement();
            con.createStatement().executeQuery("CALL TempDateTable( '" + inicio + "' , '" + fim + "' )");
            ResultSet res = st.executeQuery("SELECT tmp.data_tempo as dataset, ifnull(sum(log.quantidade), 0) as data " +
                    " FROM TempDateTable as tmp " +
                    " LEFT JOIN  _log_venda as log ON DATE(log.data) = DATE(tmp.data_tempo) and log.produto_id = '" + id +
                    "' group by 1");
            List<DataDatasetDTO> dataDatasetDTOS = new ArrayList<>();
            while (res.next()) {
                DataDatasetDTO dataDatasetDTO = new DataDatasetDTO();
                dataDatasetDTO.setDataset(res.getString("dataset"));
                dataDatasetDTO.setData(res.getInt("data"));
                dataDatasetDTOS.add(dataDatasetDTO);
            }
            st.close();
            con.close();
            return dataDatasetDTOS;
        } catch (SQLException sqle) {
            System.out.print("nao foi possivel conextar : " + sqle);
        }
        return null;
    }

    public List<String> findAllDataSet(String inicio, String fim) {
        try {
            Connection con = (Connection) DriverManager.getConnection(url, login, senha);
            Statement st = con.createStatement();
            con.createStatement().executeQuery("CALL TempDateTable( '" + inicio + "' , '" + fim + "' )");
            ResultSet res = st.executeQuery("SELECT tmp.data_tempo as dataset FROM TempDateTable as tmp group by 1;");
            List<String> datasets = new ArrayList<>();
            while (res.next()) {
                String dataset = res.getString("dataset");
                datasets.add(dataset);
            }
            st.close();
            con.close();
            return datasets;
        } catch (SQLException sqle) {
            System.out.print("nao foi possivel conextar : " + sqle);
        }
        return null;
    }
}
