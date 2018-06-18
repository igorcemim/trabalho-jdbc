package br.com.cemim.igor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import br.com.cemim.igor.classes.Placa;
import br.com.cemim.igor.sql.PlacaSql;

public class PlacaDAO implements GenericDAO<Placa> {

    private Connection connection;

    public PlacaDAO(Connection connection) {
        this.connection = connection;
    }

    private void fillStatement(PreparedStatement stmt, Placa obj, boolean update) {
        try {
            stmt.setString(1, obj.getLetras());
            stmt.setString(2, obj.getNumeros());
            if (update) {
                stmt.setInt(3, obj.getId());
            }
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao preencher os dados do PreparedStatement de placa.");
        }
    }

    public int insert(Placa obj) {
        int chavePrimaria = ERRO_OPERACAO;
        try (
            PreparedStatement stmt = connection.prepareStatement(
                PlacaSql.INSERT.getSql(),
                Statement.RETURN_GENERATED_KEYS
            )
        ) {
            this.fillStatement(stmt, obj, false);
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
        try (
            PreparedStatement stmt = connection.prepareStatement(
                PlacaSql.UPDATE.getSql()
            )
        ) {
            this.fillStatement(stmt, obj, true);
            if (stmt.executeUpdate() > 0) {
                return OPERACAO_EXECUTADA;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Ocorreu um erro ao atualizar a placa.");
        }
        return ERRO_OPERACAO;
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
                return OPERACAO_EXECUTADA;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Ocorreu um erro ao apagar a placa.");
        }
        return ERRO_OPERACAO;
    }

    private Placa fillPlaca(ResultSet rs) {
        Placa placa = null;
        try {
            placa = new Placa();
            placa.setId(rs.getInt("id"));
            placa.setLetras(rs.getString("letras"));
            placa.setNumeros(rs.getString("numeros"));
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao buscar os dados da placa.");
        }
        return placa;
    }

    public Collection<Placa> listAll() {
        Collection<Placa> lista = new ArrayList<>();

        try (
            PreparedStatement stmt = connection.prepareStatement(
                PlacaSql.LIST_ALL.getSql()
            )
        ) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Placa placa = this.fillPlaca(rs);
                lista.add(placa);
            }
            return lista;
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao listar as placas.");
        }
        return null;
    }

    public Placa findByID(int id) {
        Placa p = null;
        try (
            PreparedStatement stmt = connection.prepareStatement(
                PlacaSql.FIND_BY_ID.getSql()
            )
        ) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.first()){
                p = this.fillPlaca(rs);
            }
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao buscar a placa.");
        }
        return p;
    }
}
