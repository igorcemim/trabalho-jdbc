package br.com.cemim.igor.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection create() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String urlBD="jdbc:mysql://localhost:3306/pw1_trabalho";
        return DriverManager.getConnection(urlBD, "root", "root"); // mudar
    }

}
