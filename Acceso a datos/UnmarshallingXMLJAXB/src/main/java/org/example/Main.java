package org.example;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            // Creamos un contexto JAXB para la clase
            JAXBContext context = JAXBContext.newInstance(Catalog.class);

            // Creamos un objeto Unmarshaller
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Asociamos el objeto Catalog con el Unmarshaller
            Catalog catalog = (Catalog) unmarshaller.unmarshal(new File("books.xml"));

            // Creamos una variable ArrayList<Book> para introducir los libros del objeto Catalog
            ArrayList<Book> books;
            books = (ArrayList<Book>) catalog.getCatalog();

            // Para escribir la utilizamos el metodo writeFile
            writeFile("book.txt", books);

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeFile(String pathname, ArrayList<Book> strContent) {
        /*
         * Instaciamos una variable de clase File
         * le pasamos por parametro la ruta
         */
        File file = new File(pathname);

        try {
            // Creamos el archivo
            // Podemos añadir como parámetro true y de está forma no se sobreescribe el archivo
            BufferedWriter bw = new BufferedWriter(new FileWriter(pathname, true));

            // Escribimos en el fichero
            for (Book book : strContent) {
                bw.write(book.toString());
                bw.newLine();
            }
            // Cierra el archivo
            bw.close();
            System.out.println("Se ha escrito en el fichero exitosamente.");

        } catch (FileNotFoundException e) {
            // Lo envia por consola
            e.printStackTrace(System.out);
        } catch (IOException e) {
            // Lo envia por consola
            e.printStackTrace(System.out);
        }
    }
}