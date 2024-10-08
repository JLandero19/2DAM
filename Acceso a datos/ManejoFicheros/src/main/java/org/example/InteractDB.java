package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class InteractDB {
    // Es una constante que le añadimos el valor al llamar el constructor
    private final String nameFile = "BBDD_Coches.dat";

    // Este mapa contiene -> ["nombre del campo", Tamaño Bytes]
    private final Map<String, Integer> fields;

    // Clave Primaria de la tabla
    private final String primaryKey;

    // Es la suma de las longitudes de los atributos de un registro
    private int longReg;

    // Número de registros
    private long numReg;

    public InteractDB(Map<String, Integer> fields, String primaryKey) throws IOException {
        this.fields = fields;
        this.primaryKey = primaryKey;
        this.numReg = 0;

        // Calcula la longitud de cada registro
        // De esta forma podemos obtener [Clave, Valor]
        for (Map.Entry<String, Integer> field : fields.entrySet()) {
            // Por cada campo le estamos pidiendo el entero
            this.longReg += field.getValue();
        }
        updateNumReg();
    }

    public void updateNumReg() throws IOException {
        // Creamos la referencia al fichero
        File f = new File(nameFile);

        // Realizamos las comprobaciones previas
        if (f.exists()) {
            // Con esto conseguimos el número de registros
            // f.length() -> sacamos lo que ocupa el fichero en total
            // f.length() / longReg -> conseguimos la cantidad de registros que hay en el fichero
            this.numReg = f.length() / longReg;
        } else {
            f.createNewFile();
        }
    }

    // Este metodo es similar que importFileCSVToArrayList
    // En este caso es que estamos mirando es el archivo .dat
    public ArrayList<Car> queryAll() {
        ArrayList<Car> cars = new ArrayList<>();
        int pos = 0;
        // Utiliza LinkedHashMap porque me guarda el orden de inserción
        LinkedHashMap<String, String> result = null;

        try {
            updateNumReg();
        } catch (IOException e) {
            System.out.println("ERROR: Fichero no encontrado" + e.getMessage());
        }

        try (FileInputStream fis = new FileInputStream(this.nameFile)) {

            while (pos < this.numReg) {
                // Buffer -> Logitud del registro
                byte[] buffer = new byte[this.longReg];
                // Comprobamos la longitud de Bytes
                // Devuelve el numero de bytes leidos -> Tiene que ser igual que el longReg
                if (fis.read(buffer, 0, this.longReg) < this.longReg) {
                    return null;
                }

                int offsetField = 0;
                result = new LinkedHashMap<String, String>();

                for (Map.Entry<String, Integer> field : this.fields.entrySet()) {
                    result.put(field.getKey(), new String(buffer, offsetField, field.getValue(), StandardCharsets.UTF_8));
                    offsetField += field.getValue();
                }

                if (result != null) {
                    // Creamos un nuevo objeto Coche
                    Car newCar = new Car(result.get("matricula").trim(), result.get("marca").trim(), result.get("modelo").trim());

                    // Lo agregamos a nuestra lista de coches
                    cars.add(newCar);
                }

                pos++;
            }

        } catch (IOException io) {
            System.err.println("Error: Fichero no encontrado" + io.getMessage());
        }
        // Devolvemos la lista de coches, por defecto es NULL
        // Si hay coches en el archivo y no ha ocurrido ningún problema tendremos la lista completa.
        return cars;
    }

    // Utiliza el metodo queryAll para buscar un Coche en concreto
    public Car queryWhereID(String id) {
        ArrayList<Car> cars = new ArrayList<>();
        Car result = null;
        cars = queryAll();

        for (Car car : cars) {
            if (car.matricula.equals(id)) {
                result = car;
                break;
            }
        }

        return result;
    }

    public ArrayList<Car> insertPosition(Car car, int position) {
        ArrayList<Car> cars = new ArrayList<>();
        ArrayList<Car> result = new ArrayList<>();
        cars = queryAll();
        //position--;

        // Preguntar si tenemos que tener en cuenta que el usuario no tiene en cuenta
        // que la 1º posición es 0
        if (position >= 0 && position < cars.size()) {
            for (int i = 0; i < cars.size(); i++) {
                if (i == position) {
                    result.add(car);
                    i--;
                } else {
                    result.add(car);
                }
            }
            System.out.println("Se ha insertado el coche exitosamente");
        } else {
            System.err.println("Error: Insercción inválida, comprueba que la posición elegida es correcta.");
        }
        return result;
    }

    // Importa el archivo .csv que tenga los campos [Matricula, Marca, Modelo]
    public void importFile(String nameFile) {
        // Agregamos a un Array los registros del archivo .csv
        ArrayList<Car> cars = importFileCSVToArrayList(nameFile);
        // longMatricula -> La longitud del campo matricula
        int longMatricula = Car.bytesMatricula;
        // longMarca -> La longitud del campo marca
        int longMarca = Car.bytesMarca;
        // longModelo -> La longitud del campo modelo
        int longModelo = Car.bytesModelo;

        try (FileOutputStream fos = new FileOutputStream(this.nameFile, true)) {
            for (Car car : cars) {
                // %1$ -> el primer parametro que le pasamos
                // - -> alineación a la izquierda
                // s -> indica que es String
                String dataMatricula = String.format("%1$-" + longMatricula + "s", car.matricula); //devuelve el valor del 1er argumento en un String con longitud "longCampo" y alineado a la izquierda (gracias al uso de "-")

                // Se formatea con UTF-8
                // El offset es 0, porque [valorCampoForm] está rellenando los campos con espacios
                fos.write(dataMatricula.getBytes("UTF-8"), 0, longMatricula);

                // Repetimos con el resto
                String dataMarca = String.format("%1$-" + longMarca + "s", car.marca);
                fos.write(dataMarca.getBytes("UTF-8"), 0, longMarca);

                String dataModelo = String.format("%1$-" + longModelo + "s", car.modelo);
                fos.write(dataModelo.getBytes("UTF-8"), 0, longModelo);
            }
            System.out.println("Se ha importado el archivo exitosamente.");
        } catch (IOException e) {
            System.err.println("Error: Fichero no encontrado" + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: Lectura del fichero");
            e.printStackTrace();
        }
    }

    // Este metodo devuelve todos los registros en una lista de objetos de Car
    // Recibe por parametro la ruta de un archivo .csv
    // Este metodo lo usamos en importFile
    public ArrayList<Car> importFileCSVToArrayList(String nameFile) {
        ArrayList<Car> result = new ArrayList<Car>();
        // Declarar una variable BufferedReader
        BufferedReader br = null;
        try {
            // Crear un objeto BufferedReader al que se le pasa un objeto FileReader con el nombre del fichero
            br = new BufferedReader(new FileReader(nameFile));
            // Leer la primera línea, guardando en un String
            String text = br.readLine();

            // Repetimos está línea para que se olvide de la primera línea que sería [Matricula, Marca, Modelo]
            text = br.readLine();

            // Repetir mientras no se llegue al final del fichero
            while (text != null) {
                // Hacer lo que sea con la línea leída
                // En este ejemplo sólo se muestra por consola
                // System.out.println(text);

                result.add(new Car(text.split(",")[0], text.split(",")[1], text.split(",")[2]));

                // Leer la siguiente línea
                text = br.readLine();
            }

            // Captura de excepción por fichero no encontrado
        } catch (FileNotFoundException ex) {
            System.err.println("Error: Fichero no encontrado");
            ex.printStackTrace();

            // Captura de cualquier otra excepción
        } catch (Exception ex) {
            System.err.println("Error: Lectura del fichero");
            ex.printStackTrace();

            // Asegurar el cierre del fichero en cualquier caso
        } finally {
            try {
                // Cerrar el fichero si se ha podido abrir
                if (br != null) {
                    br.close();
                }
            } catch (Exception ex) {
                System.err.println("Error: Cerrar el fichero");
                ex.printStackTrace();
            }
        }
        return result;
    }

    // Metodos Getter y Setter
    public String getNameFile() {
        return nameFile;
    }

    public Map<String, Integer> getFields() {
        return fields;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public int getLongReg() {
        return longReg;
    }

    public void setLongReg(int longReg) {
        this.longReg = longReg;
    }

    public long getNumReg() {
        return numReg;
    }

    public void setNumReg(long numReg) {
        this.numReg = numReg;
    }
}
