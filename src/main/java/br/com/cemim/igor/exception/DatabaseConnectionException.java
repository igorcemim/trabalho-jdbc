package br.com.cemim.igor.exception;

public class DatabaseConnectionException extends Exception {

    private static final long serialVersionUID = 1000L;

    public DatabaseConnectionException(String message) {
        super(message);
    }

}
