package br.com.cemim.igor.classes;

import java.util.Collection;

import br.com.cemim.igor.dao.CarroDAO;
import br.com.cemim.igor.dao.PlacaDAO;
import br.com.cemim.igor.factory.CarroDAOFactory;
import br.com.cemim.igor.factory.PlacaDAOFactory;

// @todo toString()
public class Carro implements Comparable<Carro> {

    private int id;
    private int ano;
    private String modelo;
    private String montadora;
    private Placa placa;
    private CarroDAO carroDAO;
    private PlacaDAO placaDAO;

    public Carro() {
        this.carroDAO = new CarroDAOFactory().create();
        this.placaDAO = new PlacaDAOFactory().create();
    }

    public int getId() {
        return id;
    }

    public int getAno() {
        return ano;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMontadora() {
        return montadora;
    }

    public Placa getPlaca() {
        return placa;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setMontadora(String montadora) {
        this.montadora = montadora;
    }

    public void setPlaca(Placa placa) {
        this.placa = placa;
    }

    public int compareTo(Carro carro) {
        return Integer.compare(this.ano, carro.getAno());
    }

    public int insert() {
        return carroDAO.insert(this);
    }

    public Collection<Carro> listAll() {
        return carroDAO.listAll();
    }

    public int update() {
        return carroDAO.update(this);
    }

    public int delete() {
        return carroDAO.delete(this);
    }

    public Carro findByID() {
        return carroDAO.findByID(this.getId());
    }

}
