package br.com.cemim.igor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import br.com.cemim.igor.classes.Carro;
import br.com.cemim.igor.sql.CarroSql;

public class CarroDAO implements GenericDAO<Carro> {

    private Connection connection;

    public CarroDAO(Connection connection) {
        this.connection = connection;
    }

    private void fillStatement(PreparedStatement stmt, Carro obj, boolean update) {
        try {
            stmt.setInt(1, obj.getAno());
            stmt.setString(2, obj.getModelo());
            stmt.setString(3, obj.getMontadora());
            stmt.setInt(4, obj.getPlaca().getId());
            if (update) {
                stmt.setInt(5, obj.getId());
            }
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao preencher os dados do PreparedStatement de carro.");
        }
    }

    public int insert(Carro obj) {
        int chavePrimaria = ERRO_OPERACAO;
        try (
            PreparedStatement stmt = connection.prepareStatement(
                CarroSql.INSERT.getSql(),
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
            System.out.println("Ocorreu um erro ao inserir o carro.");
        }
        return chavePrimaria;
    }

    public int update(Carro obj) {
        try (
            PreparedStatement stmt = connection.prepareStatement(
                CarroSql.UPDATE.getSql()
            )
        ) {
            this.fillStatement(stmt, obj, true);
            if (stmt.executeUpdate() > 0) {
                return OPERACAO_EXECUTADA;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Ocorreu um erro ao atualizar o carro.");
        }
        return ERRO_OPERACAO;
    }

    public int delete(Carro obj) {
        try (
            PreparedStatement stmt = connection.prepareStatement(
                CarroSql.DELETE.getSql()
            )
        ) {
            stmt.setString(1, obj.getPlaca().getLetras());
            stmt.setString(2, obj.getPlaca().getNumeros());
            if (stmt.executeUpdate() > 0) {
                return OPERACAO_EXECUTADA;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Ocorreu um erro ao apagar o carro.");
        }
        return ERRO_OPERACAO;
    }

    private Carro fillCarro(ResultSet rs) {
        Carro carro = null;
        try {
            carro = new Carro();
            carro.setId(rs.getInt("id"));
            carro.setAno(rs.getInt("ano"));
            carro.setModelo(rs.getString("modelo"));
            carro.setMontadora(rs.getString("montadora"));
            carro.setIdPlaca(rs.getInt("placa_id"));
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao buscar os dados do carro.");
        }
        return carro;
    }

    public Collection<Carro> listAll() {
        ArrayList<Carro> lista = new ArrayList<>();

        try (
            PreparedStatement stmt = connection.prepareStatement(
                CarroSql.LIST_ALL.getSql()
            )
        ) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Carro carro = this.fillCarro(rs);
                lista.add(carro);
            }
            Collections.sort(lista);
            return lista;
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao listar os carros.");
        }
        return null;
    }

    public Carro findByID(int id) {
        Carro carro = null;
        try (
            PreparedStatement stmt = connection.prepareStatement(
                CarroSql.FIND_BY_ID.getSql()
            )
        ) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.first()) {
                carro = this.fillCarro(rs);
            }
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao buscar o carro.");
        }
        return carro;
    }
}
