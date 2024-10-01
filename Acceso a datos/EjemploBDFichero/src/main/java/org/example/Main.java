package org.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Creamos el mapa de la base de datos
        Map<String, Integer> fields = new HashMap<>();
        fields.put("matricula", 7);
        fields.put("marca", 32);
        fields.put("modelo", 32);

        try {
            BDFichero bdFichero = new BDFichero("bdcoches.dat", fields, "matricula");

            HashMap<String, String> coche = new HashMap<>();
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
//            coche = (HashMap<String, String>) bdFichero.recuperar("2222BBB");

            for (Map.Entry<String, String> data : coche.entrySet()) {
                System.out.println(data.getKey() + ": " + data.getValue());
            }

        } catch (IOException io) {
            System.out.println("Exception de E/S: " + io.getMessage());
        }

    }
}