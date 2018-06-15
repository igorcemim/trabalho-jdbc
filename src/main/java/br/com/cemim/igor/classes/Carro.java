package br.com.cemim.igor.classes;

// @todo toString()
// @todo compareTo
public class Carro implements Comparable<Carro> {

    private int id;
    private int ano;
    private String modelo;
    private String montadora;
    private Placa placa;

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
        return 0;
    }

}
