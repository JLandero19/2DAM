package org.example;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Realiza un archivo XML con 1 Coche
        //ejemplo1XML();

        // Realiza un archivo XML con lista de Coches
        ejemplo2XML();



    }

    public static void ejemplo1XML() {
        try {
            // Crear un objeto de la clase que deseamos convertir en XML
            Coche coche = new Coche("1111AAA", "Seat", "Leon");

            // Crear un contexto JAXB para la clase
            JAXBContext context = JAXBContext.newInstance(Coche.class);

            // Crear un objeto Marshaller
            Marshaller marshaller = context.createMarshaller();

            // Te formatea el archivo XML
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Te crea el archivo XML con el objeto Coche
            marshaller.marshal(coche, new File("nuevoCoche.xml"));

            // Unmarshalling Coche
            // Proceso inverso -> Ahora sacamos del archivo XML un objeto Coche
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Objeto Coche
            Coche cocheUnmarshalled = (Coche) unmarshaller.unmarshal(new File("nuevoCoche.xml"));

            // Ahora podemos mostrarlo
            System.out.println("Matricula: " + cocheUnmarshalled.getMatricula());
            System.out.println("Marca: " + cocheUnmarshalled.getMarca());
            System.out.println("Modelo: " + cocheUnmarshalled.getModelo());

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void ejemplo2XML() {
        try {
            // Crear un objeto de la clase que deseamos convertir en XML
            DBCoches dbCoches = new DBCoches();

            List<Coche> listaCoches = new ArrayList<>();
            listaCoches.add(new Coche("1111AAA", "Seat", "Leon"));
            listaCoches.add(new Coche("2222BBB", "Nissan", "GTR"));

            dbCoches.setCoches(listaCoches);

            // Crear un contexto JAXB para la clase
            JAXBContext context = JAXBContext.newInstance(DBCoches.class);

            // Crear un objeto Marshaller
            Marshaller marshaller = context.createMarshaller();

            // Te formatea el archivo XML
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Te crea el archivo XML con el objeto DBCoche
            marshaller.marshal(dbCoches, new File("listaCoches.xml"));

            // Unmarshalling DBCoche
            // Proceso inverso -> Ahora sacamos del archivo XML un objeto Coche
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Objeto DBCoche
            DBCoches cochesUnmarshalled = (DBCoches) unmarshaller.unmarshal(new File("listaCoches.xml"));

            // Mostrar la lista coches del archivo XML
            ArrayList<Coche> listaCoches2 = new ArrayList<>();
            listaCoches2 = (ArrayList<Coche>) cochesUnmarshalled.getCoches();

            for (Coche coche : listaCoches2) {
                System.out.println("Matricula: " + coche.getMatricula());
                System.out.println("Marca: " + coche.getMarca());
                System.out.println("Modelo: " + coche.getModelo());
                System.out.println("----------------------------------");
            }

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}

