package br.com.cemim.igor.classes;

// @todo toString()
public class Placa implements Comparable<Placa> {

    private String letras;
    private String numeros;

    public String getLetras() {
        return letras;
    }

    public String getNumeros() {
        return numeros;
    }

    public void setLetras(String letras) {
        this.letras = letras;
    }

    public void setNumeros(String numeros) {
        this.numeros = numeros;
    }

    public int compareTo(Placa placa) {
        return 0;
    }

}
