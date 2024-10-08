package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InteractDB {
    // Es una constante que le añadimos el valor al llamar el constructor
    private final String nameFile;

    // Este mapa contiene -> ["nombre del campo", Tamaño Bytes]
    private final Map<String, Integer> fields;

    // Clave Primaria de la tabla
    private final String primaryKey;

    // Es la suma de las longitudes de los atributos de un registro
    private int longReg;

    // Número de registros
    private long numReg;

    public InteractDB(String nameFile, Map<String, Integer> fields, String primaryKey) throws IOException {
        this.nameFile = nameFile;
        this.fields = fields;
        this.primaryKey = primaryKey;
        this.numReg = 0;

        // Calcula la longitud de cada registro
        // De esta forma podemos obtener [Clave, Valor]
        for (Map.Entry<String, Integer> campo : fields.entrySet()) {
            // Por cada campo le estamos pidiendo el entero
            this.longReg += campo.getValue();
        }

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

    // Este metodo devuelve todos los registros en una lista de objetos de Car
    public ArrayList<Car> queryAll() {
        ArrayList<Car> result = new ArrayList<Car>();
        // Declarar una variable BufferedReader
        BufferedReader br = null;
        try {
            // Crear un objeto BufferedReader al que se le pasa un objeto FileReader con el nombre del fichero
            br = new BufferedReader(new FileReader(this.nameFile));
            // Leer la primera línea, guardando en un String
            String text = br.readLine();

            // Repetimos está línea para que se olvide de la primera línea que sería Matricula, Marca, Modelo
            text = br.readLine();

            // Repetir mientras no se llegue al final del fichero
            while(text != null) {
                // Hacer lo que sea con la línea leída
                // En este ejemplo sólo se muestra por consola
                // System.out.println(text);

                result.add(new Car(text.split(",")[0], text.split(",")[1], text.split(",")[2]));

                // Leer la siguiente línea
                text = br.readLine();
            }

        // Captura de excepción por fichero no encontrado
        } catch (FileNotFoundException ex) {
            System.out.println("Error: Fichero no encontrado");
            ex.printStackTrace();

        // Captura de cualquier otra excepción
        } catch(Exception ex) {
            System.out.println("Error: Lectura del fichero");
            ex.printStackTrace();

        // Asegurar el cierre del fichero en cualquier caso
        } finally  {
            try {
                // Cerrar el fichero si se ha podido abrir
                if(br != null) {
                    br.close();
                }
            }
            catch (Exception ex) {
                System.out.println("Error: Cerrar el fichero");
                ex.printStackTrace();
            }
        }
        return result;
    }

}
