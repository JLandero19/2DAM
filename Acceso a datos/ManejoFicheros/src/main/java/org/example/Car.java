package org.example;

public class Car {
    public String matricula;
    public String marca;
    public String modelo;

    public static final Integer bytesMatricula = 7;
    public static final Integer bytesMarca = 32;
    public static final Integer bytesModelo = 32;

    public static Integer getTotalBytes() {
        return bytesMatricula + bytesMarca + bytesModelo;
    }

    public Car(String matricula, String marca, String modelo) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return matricula + " " + marca + " " + modelo;
    }
}
