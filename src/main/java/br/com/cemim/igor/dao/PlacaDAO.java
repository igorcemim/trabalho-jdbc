package br.com.cemim.igor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TreeSet;

import br.com.cemim.igor.classes.Placa;
import br.com.cemim.igor.sql.PlacaSql;

public class PlacaDAO implements GenericDAO<Placa> {

    private Connection connection;

    public PlacaDAO(Connection connection) {
        this.connection = connection;
    }

    public int insert(Placa obj) {
        int chavePrimaria = -1;
        try (
            PreparedStatement stmt = connection.prepareStatement(
                PlacaSql.INSERT.getSql(),
                Statement.RETURN_GENERATED_KEYS
            )
        ) {
            stmt.setString(1, obj.getLetras());
            stmt.setString(2, obj.getNumeros());
            stmt.execute();
            ResultSet chaves = stmt.getGeneratedKeys();
            if (chaves.next()) {
                chavePrimaria = chaves.getInt(1);
                obj.setId(chavePrimaria);
            }
        } catch(SQLException e) {
            System.out.println("Ocorreu um erro ao inserir a placa.");
        }
        return chavePrimaria;
    }

    public int update(Placa obj) {
        return 0;
    }

    public int delete(Placa obj) {
        return 0;
    }

    public TreeSet<Placa> listAll() {
        return null;
    }

    public Placa findByID(int id) {
        return null;
    }

}
