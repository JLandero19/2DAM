package org.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> fields = new HashMap<>();
        fields.put("id", 11);
        fields.put("nombre", 32);
        fields.put("raza", 50);

        try {
            BDFichero bdFichero = new BDFichero("bdanimales.dat", fields, "id");

            HashMap<String, String> animal = new HashMap<>();
            animal.put("id", "1");
            animal.put("nombre", "Nieve");
            animal.put("raza", "Podenco Andaluz");

            // insertamos
            bdFichero.insertar(animal);
            animal.clear();

            animal.put("id", "2");
            animal.put("nombre", "Rocky");
            animal.put("raza", "Husky");

            // insertamos
            bdFichero.insertar(animal);
            animal.clear();

            animal.put("id", "3");
            animal.put("nombre", "Jimmy");
            animal.put("raza", "Siba Inu");

            // insertamos
            bdFichero.insertar(animal);
            animal.clear();

            // Aqui es donde falla, cuando termina la ejecución del metodo recuperar devuelve null
            animal = (HashMap<String, String>) bdFichero.recuperar("2");
            System.out.println(animal);
//            for (Map.Entry<String, String> data : animal.entrySet()) {
//                System.out.println(data.getKey() + ": " + data.getValue());
//            }

        } catch (IOException io) {
            System.out.println("Exception de E/S: " + io.getMessage());
        }


//        // Creamos el mapa de la base de datos
//        Map<String, Integer> fields = new HashMap<>();
//        fields.put("matricula", 7);
//        fields.put("marca", 32);
//        fields.put("modelo", 32);

//        try {
//            BDFichero bdFichero = new BDFichero("bdcoches.dat", fields, "matricula");
//
//            HashMap<String, String> coche = new HashMap<>();
//            coche.put("matricula", "1111AAA");
//            coche.put("marca", "Seat");
//            coche.put("modelo", "Leon");
//
//            // insertamos
//            bdFichero.insertar(coche);
//            // .clear -> borra el contenido del HashMap
//            coche.clear();
//
//            coche.put("matricula", "2222BBB");
//            coche.put("marca", "Toyota");
//            coche.put("modelo", "CH-R");
//
//            bdFichero.insertar(coche);
//            coche.clear();
//
//            coche.put("matricula", "3333CCC");
//            coche.put("marca", "KIA");
//            coche.put("modelo", "Sportage");
//
//            bdFichero.insertar(coche);
//            coche.clear();
//
//            coche.put("matricula", "4444DDD");
//            coche.put("marca", "Ferrari");
//            coche.put("modelo", "Enzo");
//
//            bdFichero.insertar(coche);
//            coche.clear();
//
//            //coche = (HashMap<String, String>) bdFichero.recuperar("2222BBB");
//            coche = (HashMap<String, String>) bdFichero.recuperar("3333BBB");
//
//            for (Map.Entry<String, String> data : coche.entrySet()) {
//                System.out.println(data.getKey() + ": " + data.getValue());
//            }
//
//        } catch (IOException io) {
//            System.out.println("Exception de E/S: " + io.getMessage());
//        }

    }
}