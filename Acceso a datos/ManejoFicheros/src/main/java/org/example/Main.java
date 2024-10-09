package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Main {
    public static void main(String[] args) {

        LinkedHashMap<String, Integer> fields = new LinkedHashMap<>();

        fields.put("matricula", 7);
        fields.put("marca", 32);
        fields.put("modelo", 32);

        ArrayList<Car> carList = new ArrayList<Car>();

        try {
            InteractDB car = new InteractDB(fields, "matricula");


            /**
             * Preguntar al profe si el programa solo vale para los ficheros .csv
             * que tengan la estructura de Matricula,Marca,Modelo
             */
            // Importación del archivo
//            ArrayList<Car> cars = car.importFileCSVToArrayList("BBDD_Coches.csv");
//            car.importFile(cars);

            // Insercción
            Car newCar = new Car("3215FFF", "Dodge", "Challenger");
            car.insertPosition(newCar, 2);

            // Lectura
            ArrayList<Car> newCars = car.queryAll();
            if (newCars != null) {
                for (Car dataCar : newCars) {
                    System.out.println(dataCar.toString());
                }
            }

        } catch (IOException io) {
            System.err.println("Error: Fichero no encontrado" + io.getMessage());
        }
    }
}