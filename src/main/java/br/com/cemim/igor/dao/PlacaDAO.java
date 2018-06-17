package br.com.cemim.igor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
        throw new UnsupportedOperationException("Não implementado.");
    }

    public int delete(Placa obj) {
        try(
            PreparedStatement stmt = connection.prepareStatement(
                PlacaSql.DELETE.getSql()
            )
        ) {
            stmt.setString(1, obj.getLetras());
            stmt.setString(2, obj.getNumeros());
            if (stmt.executeUpdate() > 0) {
                return 1;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Ocorreu um erro ao apagar a placa.");
        }
        return -1;
    }

    public ArrayList<Placa> listAll() {
        ArrayList<Placa> lista = new ArrayList<>();

        try (
            PreparedStatement stmt = connection.prepareStatement(
                PlacaSql.LIST_ALL.getSql()
            )
        ) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Placa placa = new Placa();
                placa.setId(rs.getInt("id"));
                placa.setLetras(rs.getString("letras"));
                placa.setNumeros(rs.getString("numeros"));

                lista.add(placa);
            }
            return lista;
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao listar as placas.");
        }
        return null;
    }

    public Placa findByID(int id) {
        throw new UnsupportedOperationException("Não implementado.");
    }
}
