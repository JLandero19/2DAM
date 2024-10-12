package org.example;

import java.io.IOException;
import java.util.ArrayList;

public class TestMain {
    public static void main(String[] args) {

        try {
            InteractDB car = new InteractDB("matricula");

            /**
             * Preguntar al profe si el programa solo vale para los ficheros .csv
             * que tengan la estructura de Matricula,Marca,Modelo
             */
            // Importación del archivo (Completa)
            // La importación borra el contenido anterior que tenía el fichero
            // Se le tiene informar al usuario
//            ArrayList<Car> carList = car.importFileCSVToArrayList("BBDD_Coches.csv");
//            car.importFile("BBDD_Coches.dat", carList);

            // Insercción (Completa)
//            Car newCar = new Car("3215DCL", "Dodge", "Challenger");
//            car.insertPosition(newCar, "2");
//
//            newCar = new Car("3119CGR", "Dodge", "Charger");
//            car.insertPosition(newCar, "1");

            // Ordenación [ASC/DESC] (Completa)
            // null u ASC -> ordenación ascendente
            // DESC -> ordenación descendente
//            car.orderFile("asc");

            // Eliminación (Completado)
//            car.delete("3215FFF");

            // Modificación (Completa)
//            car.edit("3215FFF","2","Challenger");


            // Lectura (Completa)
            ArrayList<Car> carList2 = car.queryAll();

            if (carList2 != null) {
                for (Car dataCar : carList2) {
                    System.out.println(dataCar.toString());
                }
            }

        } catch (IOException io) {
            System.err.println("Error: Fichero no encontrado" + io.getMessage());
        }

    }
}
