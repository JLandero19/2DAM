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
            // Importación del archivo
//            ArrayList<Car> carList = car.importFileCSVToArrayList("BBDD_Coches.csv");
//            car.importFile("BBDD_Coches.dat", carList);
//
//            carList = car.queryAll();
//            if (carList != null) {
//                for (Car dataCar : carList) {
//                    System.out.println(dataCar.toString());
//                }
//            }

            // Insercción
//            Car newCar = new Car("3215FFF", "Dodge", "Challenger");
//            car.insertPosition(newCar, 3);

            // Ordenación [ASC/DESC]
            // null u ASC -> ordenación ascendente
            // DESC -> ordenación descendente
//            car.orderFile("desc");

            // Eliminación
//            car.delete("3215FFF");

            // Modificación
            car.edit("3215FFF","2","Challenger");


            // Lectura
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
