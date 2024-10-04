package org.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

//        Map<String, Integer> fields = new HashMap<>();
//        fields.put("id", 11);
//        fields.put("nombre", 32);
//        fields.put("raza", 50);
//
//        try {
//            BDFichero bdFichero = new BDFichero("bdanimales.dat", fields, "id");
//
//            HashMap<String, String> animales = new HashMap<>();
//            animales.put("id", "1");
//            animales.put("nombre", "Nieve");
//            animales.put("raza", "Podenco Andaluz");
//
//            // insertamos
//            bdFichero.insertar(animales);
//            animales.clear();
//
//            animales.put("id", "2");
//            animales.put("nombre", "Rocky");
//            animales.put("raza", "Husky");
//
//            // insertamos
//            bdFichero.insertar(animales);
//            animales.clear();
//
//            animales.put("id", "3");
//            animales.put("nombre", "Jimmy");
//            animales.put("raza", "Siba Inu");
//
//            // insertamos
//            bdFichero.insertar(animales);
//            animales.clear();
//
//            // Aqui es donde falla, cuando termina la ejecución del metodo recuperar devuelve null
//            animales = (HashMap<String, String>) bdFichero.recuperar("2");
//
//            for (Map.Entry<String, String> animal : animales.entrySet()) {
//                System.out.println(animal.getKey() + ": " + animal.getValue());
//            }
//
//        } catch (IOException io) {
//            System.out.println("Exception de E/S: " + io.getMessage());
//        }


        // Creamos el mapa de la base de datos
        Map<String, Integer> fields = new HashMap<>();
        fields.put("matricula", 7);
        fields.put("marca", 32);
        fields.put("modelo", 32);

        try {
            BDFichero bdFichero = new BDFichero("bdcoches.dat", fields, "matricula");

            HashMap<String, String> coche = new HashMap<>();
            coche.put("matricula", "1111AAA");
            coche.put("marca", "Seat");
            coche.put("modelo", "Leon");

            // insertamos
            bdFichero.insertar(coche);
            // .clear -> borra el contenido del HashMap
            coche.clear();

            coche.put("matricula", "2222BBB");
            coche.put("marca", "Toyota");
            coche.put("modelo", "CH-R");

            bdFichero.insertar(coche);
            coche.clear();

            coche.put("matricula", "3333CCC");
            coche.put("marca", "KIA");
            coche.put("modelo", "Sportage");

            bdFichero.insertar(coche);
            coche.clear();

            coche.put("matricula", "4444DDD");
            coche.put("marca", "Ferrari");
            coche.put("modelo", "Enzo");

            bdFichero.insertar(coche);
            coche.clear();

            // Primera mostración
            mostrarContenidoRegistro("2222BBB", bdFichero);

            // Modificación
            bdFichero.modificar("2222BBB", "marca", "Fiat");
            mostrarContenidoRegistro("2222BBB", bdFichero);

            // Borrar
//            bdFichero.borrar("3333CCC");
            bdFichero.compactar();


//            coche = (HashMap<String, String>) bdFichero.recuperar("2222BBB");
//            for (Map.Entry<String, String> data : coche.entrySet()) {
//                System.out.println(data.getKey() + ": " + data.getValue());
//            }



        } catch (IOException io) {
            System.out.println("Exception de E/S: " + io.getMessage());
        }

    }

    private static void mostrarContenidoRegistro(String unValorClave, BDFichero bdFichero) {
        HashMap<String, String> registro = (HashMap<String, String>) bdFichero.recuperar(unValorClave);

        for (Map.Entry<String, String> data : registro.entrySet()) {
            System.out.println(data.getKey() + ": " + data.getValue());
        }
    }

}