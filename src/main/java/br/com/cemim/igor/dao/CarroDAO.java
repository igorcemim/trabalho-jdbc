package br.com.cemim.igor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TreeSet;

import br.com.cemim.igor.classes.Carro;
import br.com.cemim.igor.classes.Placa;
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
        throw new UnsupportedOperationException("Não implementado.");
    }

    public int delete(Carro obj) {
        try(
            PreparedStatement stmt = connection.prepareStatement(
                CarroSql.DELETE.getSql()
            )
        ) {
            stmt.setString(1, obj.getPlaca().getLetras());
            stmt.setString(2, obj.getPlaca().getNumeros());
            if (stmt.executeUpdate() > 0) {
                return 1;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Ocorreu um erro ao apagar o carro.");
        }
        return -1;
    }

    public TreeSet<Carro> listAll() {
        TreeSet<Carro> lista = new TreeSet<>();

        try (
            PreparedStatement stmt = connection.prepareStatement(
                CarroSql.LIST_ALL.getSql()
            )
        ) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Placa placa = new Placa();
                placa.setId(rs.getInt("placa_id"));
                placa.setLetras(rs.getString("letras"));
                placa.setNumeros(rs.getString("numeros"));

                Carro carro = new Carro();
                carro.setId(rs.getInt("id"));
                carro.setAno(rs.getInt("ano"));
                carro.setModelo(rs.getString("modelo"));
                carro.setMontadora(rs.getString("montadora"));
                carro.setPlaca(placa);

                lista.add(carro);
            }
            return lista;
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao listar os carros.");
        }
        return null;
    }

    public Carro findByID(int id) {
        throw new UnsupportedOperationException("Não implementado.");
    }
}
