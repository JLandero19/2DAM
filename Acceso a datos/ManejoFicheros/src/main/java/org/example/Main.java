package org.example;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        try {
            InteractDB car = new InteractDB("matricula");
            int option = 0;
            do {
                /*
                 * El Usuario no necesita hacer una importación de primera mano
                 * El Usuario si elige Insertar tenemos que contemplar si existe o no el fichero
                 * "" si elige Ordenación tenemos que contemplar si existe el fichero y si hay contenido
                 * "" si elige Eliminación tenemos que contemplar si existe el fichero y si hay contenido antes de Eliminar
                 * "" si elige Editar tenemos que contemplar si existe el fichero y si hay contenido antes de Eliminar
                 * Mostrar el contenido de la Inserción/Edición/Eliminación y mostrar la lista de Coches en caso de Ordenación
                 */
                switch (option) {
                    case 1:
                        // Importación
                        break;
                    case 2:
                        // Insertar un Registro
                        break;
                    case 3:
                        // Ordenación
                        break;
                    case 4:
                        // Eliminar un Registro
                        break;
                    case 5:
                        // Editar un Registro
                        break;
                    default:
                        // Importación
                        break;
                }

            } while (option <= 0);

        } catch (IOException io) {
            System.err.println("Error: Fichero no encontrado" + io.getMessage());
        }
    }

    public void importDataBase() {

    }

    public void insertRegister() {

    }

    public void orderDataBase() {

    }

    public void deleteRegister() {

    }

    public void editRegister() {

    }
}