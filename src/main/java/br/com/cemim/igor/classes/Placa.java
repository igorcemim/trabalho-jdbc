package br.com.cemim.igor.classes;

import java.util.Collection;

import br.com.cemim.igor.dao.PlacaDAO;
import br.com.cemim.igor.factory.PlacaDAOFactory;

// @todo toString()
public class Placa {

    private int id;
    private String letras;
    private String numeros;
    private PlacaDAO placaDAO;

    public Placa() {
        this.placaDAO = new PlacaDAOFactory().create();
    }

    public int getId() {
        return id;
    }

    public String getLetras() {
        return letras;
    }

    public String getNumeros() {
        return numeros;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLetras(String letras) {
        this.letras = letras;
    }

    public void setNumeros(String numeros) {
        this.numeros = numeros;
    }

    public int insert() {
        return placaDAO.insert(this);
    }

    public Collection<Placa> listAll() {
        return placaDAO.listAll();
    }

    public int update() {
        return placaDAO.update(this);
    }

    public int delete() {
         return placaDAO.delete(this);
    }

    public Placa findByID() {
        return placaDAO.findByID(this.getId());
    }

    public String toString() {
        return String.format(
            "Placa {id = %d, ano = %s, modelo = %s}",
            this.id,
            this.letras,
            this.numeros
        );
    }
}
