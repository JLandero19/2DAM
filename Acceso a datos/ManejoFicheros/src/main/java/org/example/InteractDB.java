package org.example;

import com.sun.istack.internal.Nullable;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class InteractDB {
    // Es una constante que le añadimos el valor al llamar el constructor
    private final String nameFile = "BBDD_Coches.dat";

    // Este mapa contiene -> ["nombre del campo", Tamaño Bytes]
    private final LinkedHashMap<String, Integer> fields = new LinkedHashMap<String, Integer>() {{
        put("matricula", 7);
        put("marca", 32);
        put("modelo", 32);
    }};

    public Integer getTotalBytes() {
        return this.fields.get("matricula") + this.fields.get("marca") + this.fields.get("modelo");
    }

    // Clave Primaria de la tabla
    private final String primaryKey;

    // Es la suma de las longitudes de los atributos de un registro
    private int longReg;

    // Número de registros
    private long numReg;

    public InteractDB(String primaryKey) throws IOException {
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
        File f = new File(this.nameFile);

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

                // Aquí creamos la lista result
                for (Map.Entry<String, Integer> field : this.fields.entrySet()) {
                    result.put(field.getKey(), new String(buffer, offsetField, field.getValue(), StandardCharsets.UTF_8));
                    offsetField += field.getValue();
                }

                // Si result no está nulo se ejecuta lo siguiente
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

    // Importa el archivo .csv que tenga los campos [Matricula, Marca, Modelo]
    public boolean importFile(String nameFile,ArrayList<Car> cars) {
        // Agregamos a un Array los registros del archivo .csv
        try (FileOutputStream fos = new FileOutputStream(nameFile, true)) {
            for (Car car : cars) {
                // %1$ -> el primer parametro que le pasamos
                // - -> alineación a la izquierda
                // s -> indica que es String
                String dataMatricula = String.format("%1$-" + this.fields.get("matricula") + "s", car.matricula); //devuelve el valor del 1er argumento en un String con longitud "longCampo" y alineado a la izquierda (gracias al uso de "-")

                // Se formatea con UTF-8
                // El offset es 0, porque [valorCampoForm] está rellenando los campos con espacios
                fos.write(dataMatricula.getBytes("UTF-8"), 0, this.fields.get("matricula"));

                // Repetimos con el resto
                String dataMarca = String.format("%1$-" + this.fields.get("marca") + "s", car.marca);
                fos.write(dataMarca.getBytes("UTF-8"), 0, this.fields.get("marca"));

                String dataModelo = String.format("%1$-" + this.fields.get("modelo") + "s", car.modelo);
                fos.write(dataModelo.getBytes("UTF-8"), 0, this.fields.get("modelo"));
            }
            File fileDAT = new File(this.nameFile);
            if (fileDAT.length() > 0) {
                updateNumReg();
                return true;
            }

        } catch (IOException e) {
            System.err.println("Error: Fichero no encontrado" + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: Lectura del fichero");
            e.printStackTrace();
        }

        return false;
    }

    // Este metodo devuelve todos los registros en una lista de objetos de Car
    // Recibe por parametro la ruta de un archivo .csv
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

    // Inserta los datos de 1 coche en una posición predeterminada entre 1-[numeroRegistro]
    public boolean insertPosition(Car car, int position) throws IOException {
        // Comprobamos que no haya un coche con la misma matricula
        Car searchCar = null;
        searchCar = queryWhereID(car.matricula);
        if (searchCar != null) {
            System.err.println("ERROR: Ya existe un coche con la matricula " + car.matricula);
            return false;
        }

        // Creamos un ArrayList con los coches del Fichero.dat
        ArrayList<Car> cars = queryAll();

        // Por seguridad hacemos una copia por si da un ERROR más adelante
        importFile("backup.dat",cars);

        // Creamos un nuevo ArrayList donde irá el nuevo contenido junto con el actual
        ArrayList<Car> newCars = new ArrayList<>();

        // Eliminamos el contenido del archivo
        deleteAllContentFile(this.nameFile);

        // Este nos permite introducir todos los coches dentro del ArrayList newCar
        for (int i = 0; i < cars.size(); i++) {
            // Si la posición es la buscamos entra el objeto que le metimos por parametros.
            if (i == (position-1)) {
                newCars.add(car);
            }
            newCars.add(cars.get(i));
        }
        // Utilizamos el metodo para importar ficheros que ya utilizamos en la importación
        // ya que crea un fichero nuevo e introduce correctamente
        boolean confirm = importFile(this.nameFile, newCars);

        // Si confirm devuelve true muestra el mensaje exitoso
        if (confirm) {
            System.out.println("Se ha insertado el coche " + car.toString());
            // Eliminamos el backup.dat cuando sabemos que se ha ejecutado exitosamente
            File fileData = new File("backup.dat");
            fileData.delete();
            return true;
        } else {
            System.err.println("No se ha insertado el coche " + car.toString());
            return false;
        }
    }

    // Ordenar el fichero por el campo matricula
    // Por defecto será Ascendente
    public void orderFile(@Nullable String mode) {
        // Creamos un ArrayList con los coches del Fichero.dat
        ArrayList<Car> cars = queryAll();

        // Por seguridad hacemos una copia por si da un ERROR más adelante
        importFile("backup.dat",cars);

        // Creamos un nuevo ArrayList donde irá el nuevo contenido junto con el actual
        Collections.sort(cars, new Comparator<Car>() {
            @Override
            public int compare(Car c1, Car c2) {
                if (mode == "DESC" || mode == "desc") {
                    // Versión descendente
                    return new String(c2.matricula).compareTo(new String(c1.matricula));
                }
                // Versión ascendente
                return new String(c1.matricula).compareTo(new String(c2.matricula));
            }
        });

        // Eliminamos el contenido del archivo
        deleteAllContentFile(this.nameFile);

        // ya que crea un fichero nuevo e introduce correctamente
        boolean confirm = importFile(this.nameFile, cars);

        // Si confirm devuelve true muestra el mensaje exitoso
        if (confirm) {
            System.out.println("Se ha ordenado los datos de los coches exitosamente");
            // Eliminamos el backup.dat cuando sabemos que se ha ejecutado exitosamente
            File fileData = new File("backup.dat");
            fileData.delete();
        } else {
            System.err.println("ERROR: No ha sido posible la ordenación de los datos");
        }
    }

    public void deleteAllContentFile(String nameFile) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(nameFile));
            bw.write("");
            bw.close();

        } catch (IOException e) {
            System.err.println("Error: Fichero no encontrado");
        }
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
