package br.com.cemim.igor.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.cemim.igor.exception.DatabaseConnectionException;

public class ConnectionFactory {

    public Connection create() throws ClassNotFoundException, DatabaseConnectionException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String urlBD="jdbc:mysql://localhost:3306/pw1_trabalho";
            return DriverManager.getConnection(urlBD, "root", "root");
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Não foi possível conectar-se ao banco de dados.");
        }
    }

}
