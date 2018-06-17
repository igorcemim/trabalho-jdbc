package br.com.cemim.igor.factory;

import br.com.cemim.igor.Application;
import br.com.cemim.igor.dao.PlacaDAO;

public class PlacaDAOFactory {

    public PlacaDAO create() {
        if (Application.connection == null) {
            throw new java.lang.RuntimeException("Conexão não inicializada.");
        }
        return new PlacaDAO(Application.connection);
    }

}
