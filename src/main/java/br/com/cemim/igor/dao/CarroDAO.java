package br.com.cemim.igor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TreeSet;

import br.com.cemim.igor.classes.Carro;
import br.com.cemim.igor.sql.CarroSql;

public class CarroDAO implements GenericDAO<Carro> {

    private Connection connection;

    public CarroDAO(Connection connection) {
        this.connection = connection;
    }

    public int insert(Carro obj) {
        int chavePrimaria = -1;
        try (
            PreparedStatement stmt = connection.prepareStatement(
                CarroSql.INSERT.getSql(),
                Statement.RETURN_GENERATED_KEYS
            )
        ) {
            stmt.setInt(1, obj.getAno());
            stmt.setString(2, obj.getModelo());
            stmt.setString(3, obj.getMontadora());
            stmt.setInt(4, obj.getPlaca().getId());
            stmt.execute();
            ResultSet chaves = stmt.getGeneratedKeys();
            if (chaves.next()) {
                chavePrimaria = chaves.getInt(1);
                obj.setId(chavePrimaria);
            }
        } catch(SQLException e) {
            System.out.println("Ocorreu um erro ao inserir o carro.");
        }
        return chavePrimaria;
    }

    public int update(Carro obj) {
        return 0;
    }

    public int delete(Carro obj) {
        return 0;
    }

    public TreeSet<Carro> listAll() {
        return null;
    }

    public Carro findByID(int id) {
        return null;
    }

}
