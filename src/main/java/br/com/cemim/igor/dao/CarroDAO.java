package br.com.cemim.igor.dao;

import java.util.TreeSet;

import br.com.cemim.igor.classes.Carro;

public class CarroDAO implements GenericDAO<Carro> {

    public boolean insert(Carro obj) {
        return false;
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
