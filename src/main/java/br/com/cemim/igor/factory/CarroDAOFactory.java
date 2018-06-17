package br.com.cemim.igor.factory;

import br.com.cemim.igor.Application;
import br.com.cemim.igor.dao.CarroDAO;

public class CarroDAOFactory {

    public CarroDAO create() {
        if (Application.connection == null) {
            throw new java.lang.RuntimeException("Conexão não inicializada.");
        }
        return new CarroDAO(Application.connection);
    }

}
