package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        HashMap<String, Integer> fields = new HashMap<String, Integer>();

        fields.put("matricula", 7);
        fields.put("marca", 32);
        fields.put("modelo", 32);

        ArrayList<Car> carList = new ArrayList<Car>();

        try {
            InteractDB car = new InteractDB("BBDD_Coches.csv", fields, "matricula");
            carList = car.queryAll();

            for (Car c : carList) {
                System.out.println(c.matricula + " " + c.marca + " " + c.modelo);
            }

        } catch (IOException io) {
            System.out.println(io.getMessage());
        }

        // https://javiergarciaescobedo.es/programacion-en-java/15-ficheros/44-leer-un-fichero-de-texto-linea-a-linea#:~:text=Se%20utiliza%20el%20m%C3%A9todo%20readLine,io.
    }
}