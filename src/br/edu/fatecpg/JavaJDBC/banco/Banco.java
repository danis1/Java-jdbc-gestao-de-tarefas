package br.edu.fatecpg.JavaJDBC.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Banco {
    public static Connection connect() throws SQLException{
        try {
            var jdbcUrl = "jdbc:postgresql://localhost:5432/db_fatec";
            var user = "fatec";
            var password = "fatec777";
            return DriverManager.getConnection(jdbcUrl, user, password);

        } catch (SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }
}
