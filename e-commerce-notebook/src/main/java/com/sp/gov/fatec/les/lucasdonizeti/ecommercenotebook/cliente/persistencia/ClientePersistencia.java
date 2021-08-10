package com.sp.gov.fatec.les.lucasdonizeti.ecommercenotebook.cliente.persistencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.*;
import java.util.UUID;

/**
  *
  *author LucasDonizeti
*/
@Repository
public class ClientePersistencia {

    private String url;
    private String login;
    private String senha;

    @Autowired
    ClientePersistencia(@Value("${spring.datasource.url}") String url,
                   @Value("${spring.datasource.username}") String login,
                   @Value("${spring.datasource.password}") String senha){
        this.url = url;
        this.login = login;
        this.senha = senha;
    }

    @Transactional
    public void adcRank(UUID clienteID, int adc) throws SQLException {
        try {
            Connection con = DriverManager.getConnection(url, login, senha);
            String query = " update _cliente as c set c.rank = (c.rank + " + adc +" ) where c.id = '" + clienteID +"' ";
            System.out.println("ClientePersistence: "  + query);
            con.createStatement().executeUpdate(query);
            con.close();
        } catch (SQLException sqle) {
            System.out.print(sqle);
        }
    }
}
