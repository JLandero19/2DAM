package org.example;

public class Car {
    public String matricula;
    public String marca;
    public String modelo;

    public Car(String matricula, String marca, String modelo) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return matricula + " | " + marca + " | " + modelo;
    }
}
