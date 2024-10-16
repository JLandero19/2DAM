package org.example;

import com.sun.istack.internal.Nullable;

import java.io.IOException;
import java.util.*;

public class Main {
    // Clase Scanner -> permite introducir texto por teclado
    public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {

        int option = 0;
        String selection = "";
        do {
            // Mostramos el menú de opciones
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
                System.err.println("ERROR Opcion no valida");
                continue;
            }

            // Esto ejecutará la opción del menú correspondiente
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
                    insertRegister();
                    break;
                case 3:
                    // Ordenación
                    orderDataBase();
                    break;
                case 4:
                    // Eliminar un Registro
                    deleteRegister();
                    break;
                case 5:
                    // Editar un Registro
                    editRegister();
                    break;
                case 6:
                    // Lectura de la Base de Datos
                    readDataBase();
                    break;
                case 7:
                    // Salir
                    System.out.println("¿Desea salir?[Si/No]");
                    String salir = scan.nextLine();
                    if (salir.equals("Si") || salir.equals("si")) {
                        System.exit(0);
                    }
                    break;
                default:
                    System.err.println("ERROR Opcion no valida");
                    break;
            }
        } while (option <= 0 || option > 7 || !InteractDB.isInteger(selection));

    }

    // Importación de la base de datos
    public static void importDataBase() throws IOException {
        // Explicación para el usuario de como funciona
        System.out.println("Para importar un archivo .csv escriba la ruta del archivo \n(Ejemplo: C:\\Users\\JL19\\NombreFichero.csv)");
        System.out.println("También puede agregar archivo .csv dentro de la carpeta raíz del programa ManejoFicheros \n(Ejemplo: NombreFichero.csv)");
        String nameFile = scan.nextLine();

        InteractDB car = new InteractDB("matricula");

        // Preguntamos si contiene la cadena .csv
        if (nameFile.contains(".csv")) {
            // Le decimos que sobreescribirá los datos y si desea continuar
            System.out.println("Los datos que estaban serán sobrescritos ¿Está seguro de continuar? [Si/No]");
            String confirm = scan.nextLine();
            if (confirm.equals("Si") || confirm.equals("si")) {
                // Importamos el fichero .csv
                ArrayList<Car> cars = car.importFileCSVToArrayList(nameFile);

                // Hacemos la importación al fichero .dat
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
        System.out.println();
        main(null);
    }

    // Inserción de un registro
    public static void insertRegister() {
        InteractDB car;
        String position = null;
        try {
            car = new InteractDB("matricula");
            // Preguntamos si existe el fichero .dat
            if (InteractDB.existsFile("BBDD_Coches.dat")) {
                // Sacamos los registros
                ArrayList<Car> cars = car.queryAll();

                // Preguntamos si es mayor que 0 el numero de registros
                if (cars.size() > 0) {
                    String confirm = null;
                    // Preguntamos si quieres meter los datos en una posición determinada
                    do {
                        // Preguntamos si quiere insertar el registro en una posición específica
                        System.out.println("¿Quieres insertar un registro en una posición determinada?[Si/No]");
                        confirm = scan.nextLine();


                        if (confirm.equals("Si") || confirm.equals("si")) {
                            do {
                                // En caso afirmativo, preguntamos la posición
                                System.out.println("Elige la posición que quieras [1-" + (car.getNumReg()+1) + "]");
                                position = scan.nextLine();

                                if (InteractDB.isInteger(position) && position.length() == 1 && Integer.parseInt(position) >= 1 && Integer.parseInt(position) <= (car.getNumReg()+1)) {
                                    // Si ha sido correcto ejecutamos la siguiente función
                                    insertSupport(position);
                                } else {
                                    System.err.println("ERROR al insertar una posición de registro");
                                    continue;
                                }
                            } while (!InteractDB.isInteger(position) || position.length() != 1 || Integer.parseInt(position) < 1 || Integer.parseInt(position) > (car.getNumReg()+1));
                        } else if (confirm.equals("No") || confirm.equals("no")) {
                            // En caso negativo se ejecuta la siguiente función
                            // por defecto position es null, por lo tanto, se le está pasando un parámetro nullable
                            insertSupport(position);
                        } else {
                            confirm = null;
                            System.err.println("ERROR El valor introducido no es valido");
                        }
                    } while (confirm == null);

                } else {
                    // En caso negativo se ejecuta la siguiente función
                    // por defecto position es null, por lo tanto, se le está pasando un parámetro nullable
                    insertSupport(position);
                }
            } else {
                // En caso negativo se ejecuta la siguiente función
                // por defecto position es null, por lo tanto, se le está pasando un parámetro nullable
                insertSupport(position);
            }
        } catch (IOException e) {
            System.err.println("ERROR de lectura de fichero");
        }
        System.out.println();
        main(null);
    }

    public static void insertSupport(@Nullable String position) throws IOException {
        InteractDB car = new InteractDB("matricula");
        String matricula;
        String marca;
        String modelo;
        boolean insertConfirm = false;
        do {
            // Preguntamos por la matricula
            do {
                System.out.println("Introduce tu matricula:");
                matricula = scan.nextLine();

                if (matricula.length() != 7) {
                    System.err.println("ERROR valor de matricula no es valido");
                }

            } while (matricula == null || matricula.length() != 7);

            // Preguntamos por la marca
            do {
                System.out.println("Introduce tu marca:");
                marca = scan.nextLine();

                if (marca.length() <= 0 || marca.length() > 32) {
                    System.err.println("ERROR valor de marca no es valido");
                }

            } while (marca.length() <= 0 || marca.length() > 32);

            // Preguntamos por el modelo
            do {
                System.out.println("Introduce tu modelo:");
                modelo = scan.nextLine();

                if (modelo.length() <= 0 || modelo.length() > 32) {
                    System.err.println("ERROR valor de modelo no es valido");
                }

            } while (modelo.length() <= 0 || modelo.length() > 32);

            Car newCar = new Car(matricula, marca, modelo);
            // car.insertPosition(newCar, position) -> devuelve un boolean
            insertConfirm = car.insertPosition(newCar, position);
        } while (!insertConfirm);
    }

    public static void orderDataBase() {
        InteractDB car = null;
        try {
            car = new InteractDB("matricula");
            String mode = null;
            do {
                System.out.println("¿Desea ordenar de forma ascendente o descente? [ASC/DESC]");
                mode = scan.nextLine();
                if (mode.equals("DESC") || mode.equals("desc")) {
                    car.orderFile(mode);
                    readDataBase();
                } else if (mode.equals("ASC") || mode.equals("asc")) {
                    car.orderFile(mode);
                    readDataBase();
                } else {
                    mode = null;
                    System.err.println("ERROR formato de ordenación incorrecto");
                }
            } while (mode == null);
        }catch (IOException e) {
            System.err.println("ERROR Fichero no encontrado");
        }
        System.out.println();
        main(null);
    }

    public static void deleteRegister() {
        InteractDB car = null;
        try {
            car = new InteractDB("matricula");
            // Comprobamos que no esté vacia la base de datos
            if (car.queryAll().size() > 0) {
                // Le preguntamos por la matrícula
                System.out.println("Introduce la matricula del coche que desea eliminar:");
                String matricula = scan.nextLine();
                // Para terminar ejecutamos el metodo delete de la clase InteractDB
                car.delete(matricula);
            } else {
                System.err.println("No hay registros por el momento");
            }
        }catch (IOException e) {
            System.err.println("ERROR Fichero no encontrado");
        }
        System.out.println();
        main(null);
    }

    public static void editRegister() {
        InteractDB car = null;
        try {
            // Instanciamos el objeto InteractDB
            car = new InteractDB("matricula");
            // Creamos un objeto Car que utilizaremos más adelante
            Car confirmCar = null;
            ArrayList<Car> cars = car.queryAll();
            if (cars.size() >= 1) {
                // Creamos esta variable para la siguiente pregunta
                String confirm = null;
                do {
                    System.out.println("¿Quieres buscar el registro por matricula o por posición?[Mat/Pos]");
                    confirm = scan.nextLine();
                    // Si el usuario no teclea una de las siguientes opciones el programa te envia mensaje de error
                    if (!confirm.equals("Mat") && !confirm.equals("mat") && !confirm.equals("Pos") && !confirm.equals("pos")) {
                        System.err.println("ERROR El valor introducido no es valido");
                    }
                } while (!confirm.equals("Mat") && !confirm.equals("mat") && !confirm.equals("Pos") && !confirm.equals("pos"));

                // Según la opción entra en la opción matrícula o posición
                // Matrícula
                if (confirm.equals("Mat") || confirm.equals("mat")) {
                    do {
                        // Preguntamos por la matricula
                        System.out.println("Introduce la matricula del coche que desea editar:");
                        String matricula = scan.nextLine();
                        // Confirmamos si existe
                        confirmCar = car.queryWhereID(matricula);
                        if (confirmCar != null) {
                            // Este metodo es el que termina la ejecución de la edición de un registro
                            // El mode que es un boolean indica que si es true utilizamos la matrícula para editar
                            // Si el mode es false utilizamos la posición
                            supportEditRegister(true, matricula);
                        } else {
                            System.err.println("ERROR No existe el coche especificado");
                            continue;
                        }
                    } while (confirmCar == null);
                // Posición
                } else if (confirm.equals("Pos") || confirm.equals("pos")) {
                    String position = null;
                    do {
                        // Preguntamos la posición que queremos editar
                        // cars.size() -> utilizamos para ver la última posición editable
                        System.out.println("¿Que posición quieres editar?[1-" + cars.size() + "]");
                        position = scan.nextLine();

                        // Utilizamos este metodo para confirmar si existe un registro en esta posición
                        confirmCar = car.queryWherePosition(position);
                        if (confirmCar != null) {
                            // Este metodo es el que termina la ejecución de la edición de un registro
                            // El mode que es un boolean indica que si es true utilizamos la matrícula para editar
                            // Si el mode es false utilizamos la posición
                            supportEditRegister(false, position);
                        } else {
                            System.err.println("ERROR No existe el coche especificado");
                            continue;
                        }
                    } while (position == null || !InteractDB.isInteger(position) || confirmCar == null);
                }

            } else {
                System.err.println("No hay registros por el momento");
            }
        }catch (IOException e) {
            System.err.println("ERROR Fichero no encontrado");
        }
        System.out.println();
        main(null);
    }

    // Este metodo es un soporte que se utiliza para ayudar al metodo editRegister()
    public static void supportEditRegister(boolean mode, String type) throws IOException {
        String numField = null;
        String edit = null;
        InteractDB car = new InteractDB("matricula");
        do {
            // Preguntamos que campo queremos editar
            System.out.println("¿Que campo desea editar?");
            System.out.println("[1] Marca");
            System.out.println("[2] Modelo");
            numField = scan.nextLine();

            if (numField.length() == 1 && InteractDB.isInteger(numField) && Integer.parseInt(numField) >= 1 && Integer.parseInt(numField) <= 2) {
                // Si entra en este if, pregunta por el valor a introducir
                System.out.println("Introduce el nuevo valor");
                edit = scan.nextLine();
                // Según el metodo elegido, será el de matrícula o el de posición
                if (edit != null && !edit.isEmpty()) {
                    if (mode) {
                        car.editMatricula(type, numField, edit);
                    } else {
                        car.editPosition(type, numField, edit);
                    }
                } else {
                    System.err.println("ERROR El nuevo valor no es valido");
                    continue;
                }

            } else {
                System.err.println("ERROR El campo introducido no es valido");
            }
        } while (edit == null || edit.isEmpty() || Integer.parseInt(numField) < 1 || Integer.parseInt(numField) > 2);
    }

    // Este metodo se utiliza para la lectura de los registros
    public static void readDataBase() {
        InteractDB car = null;
        try {
            car = new InteractDB("matricula");
            // Sacamos todos los registros
            ArrayList<Car> carList2 = car.queryAll();
            // Si hay 1 o mas registros los muestra en caso contrario nos envia un mensaje de que no hay registros
            if (InteractDB.existsFile("BBDD_Coches.dat") && carList2.size() > 0) {
                System.out.println("Matricula | Marca | Modelo");
                if (carList2 != null) {
                    for (Car dataCar : carList2) {
                        System.out.println(dataCar.toString());
                    }
                }
            } else {
                System.err.println("No hay registros por el momento");
            }
        } catch (IOException e) {
            System.err.println("ERROR de lectura de fichero");
        }
        System.out.println();
        main(null);
    }
}