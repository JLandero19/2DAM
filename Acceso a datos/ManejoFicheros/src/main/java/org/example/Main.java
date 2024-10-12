package org.example;

import java.io.IOException;
import java.util.*;

public class Main {
    // Clase Scanner -> permite introducir texto por teclado
    public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {

        int option = 0;
        String selection = "";
        do {
            /*
             * El Usuario no necesita hacer una importación de primera mano
             * El Usuario si elige Insertar tenemos que contemplar si existe o no el fichero
             * "" si elige Ordenación tenemos que contemplar si existe el fichero y si hay contenido
             * "" si elige Eliminación tenemos que contemplar si existe el fichero y si hay contenido antes de Eliminar
             * "" si elige Editar tenemos que contemplar si existe el fichero y si hay contenido antes de Eliminar
             * Mostrar el contenido de la Inserción/Edición/Eliminación y mostrar la lista de Coches en caso de Ordenación
             */

            System.out.println("Seleccione una opcion [1-6]:");
            System.out.println("[1] Importar archivo .csv");
            System.out.println("[2] Insertar un registro en la base de datos");
            System.out.println("[3] Ordenar la base de datos");
            System.out.println("[4] Eliminar un registro en la base de datos");
            System.out.println("[5] Editar un registro en la base de datos");
            System.out.println("[6] Lectura de la base de datos");
            System.out.println("[7] Salir");

            // Leemos lo que escriba el usuario
            selection = scan.nextLine();

            // Lo pasamos a entero
            if (InteractDB.isInteger(selection)) {
                option = Integer.parseInt(selection);
            } else {
                System.err.println("Opcion no valida");
                continue;
            }

            switch (option) {
                case 1:
                    // Importación
                    try {
                        importDataBase();
                    } catch (IOException e) {
                        System.out.println("ERROR al importar el archivo .csv");
                    }
                    break;
                case 2:
                    // Insertar un Registro
                    System.out.println("Insertar un registro en la base de datos");
                    break;
                case 3:
                    // Ordenación
                    System.out.println("Ordenar la base de datos");
                    break;
                case 4:
                    // Eliminar un Registro
                    System.out.println("Eliminar un registro en la base de datos");
                    break;
                case 5:
                    // Editar un Registro
                    System.out.println("Editar un registro en la base de datos");
                    break;
                case 6:
                    // Lectura de la Base de Datos
                    readDataBase();
                    break;
                case 7:
                    System.out.println("¿Desea salir?[Si/No]");
                    String salir = scan.nextLine();
                    if (salir.equals("Si") || salir.equals("si")) {
                        System.exit(0);
                    }
                    break;
                default:
                    System.err.println("Opcion no valida");
                    break;
            }
        } while (option <= 0 || option > 7 || !InteractDB.isInteger(selection));
    }

    public static void importDataBase() throws IOException {
        System.out.println("Para importar un archivo .csv escriba la ruta del archivo \n(Ejemplo: C:\\Users\\JL19\\NombreFichero.csv)");
        System.out.println("También puede agregar archivo .csv dentro de la carpeta raíz del programa ManejoFicheros \n(Ejemplo: NombreFichero.csv)");
        String nameFile = scan.nextLine();

        InteractDB car = new InteractDB("matricula");

        if (nameFile.contains(".csv")) {
            System.out.println("Los datos que estaban serán sobrescritos ¿Está seguro de continuar? [Si/No]");
            String confirm = scan.nextLine();
            if (confirm.equals("Si") || confirm.equals("si")) {
                ArrayList<Car> cars = car.importFileCSVToArrayList(nameFile);

                if (car.importFile("BBDD_Coches.dat", cars)) {
                    System.out.println("Los datos del fichero " + nameFile + " se han importado exitosamente" );
                } else {
                    System.err.println("ERROR al importar el archivo .csv");
                    importDataBase();
                }
            } else {
                System.out.println("Se ha cancelado la importación.");
            }

        } else {
            System.err.println("ERROR El fichero " + nameFile + " no es valido");
        }
        main(null);
    }

    public static void insertRegister() {
        main(null);
    }

    public static void orderDataBase() {
        main(null);
    }

    public static void deleteRegister() {
        main(null);
    }

    public static void editRegister() {
        main(null);
    }

    public static void readDataBase() {
        InteractDB car = null;
        try {
            if (InteractDB.existsFile("BBDD_Coches.dat")) {
                car = new InteractDB("matricula");

                ArrayList<Car> carList2 = car.queryAll();
                System.out.println("Matricula | Marca | Modelo");
                if (carList2 != null) {
                    for (Car dataCar : carList2) {
                        System.out.println(dataCar.toString());
                    }
                }
            } else {
                System.out.println("No existe el fichero");
            }
        } catch (IOException e) {
            System.err.println("ERROR de lectura de fichero");
        }
        main(null);
    }
}