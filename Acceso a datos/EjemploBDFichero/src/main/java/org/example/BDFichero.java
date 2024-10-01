package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class BDFichero {
    // Es una constante que le añadimos el valor al llamar el constructor
    private final String nombreFich;

    // Este mapa contiene -> ["nombre del campo", Tamaño Bytes]
    private final Map<String, Integer> campos;

    // Clave Primaria de la tabla
    private final String campoClave;

    // Es la suma de las longitudes de los atributos de un registro
    private int longReg;

    private long numReg;

    private long numRegMarcadosBorrado;

    // Alt + Insert para añadir código automatico (Constructor, Getter/Setter, etc...)
    // Metodo Construtor                                                               // Necesitamos la posibilidad de lanzar un IOException
    public BDFichero(String nombreFich, Map<String, Integer> campos, String campoClave) throws IOException {
        this.nombreFich = nombreFich;
        this.campos = campos;
        this.campoClave = campoClave;
        this.numReg = 0;
        this.numRegMarcadosBorrado = 0;

        // Calcula la longitud de cada registro
        // De esta forma podemos obtener [Clave, Valor]
        for (Map.Entry<String, Integer> campo : campos.entrySet()) {
            // Por cada campo le estamos pidiendo el entero
            this.longReg += campo.getValue();
        }

        // Creamos la referencia al fichero
        File f = new File(nombreFich);

        // Realizamos las comprobaciones previas
        if (f.exists()) {
            // Con esto conseguimos el número de registros
            // f.length() -> sacamos lo que ocupa el fichero
            this.numReg = f.length() / longReg;
        } else {
            f.createNewFile();
        }
    }

    public String getNombreFich() {
        return nombreFich;
    }

    public Map<String, Integer> getCampos() {
        return campos;
    }

    public String getCampoClave() {
        return campoClave;
    }

    public int getLongReg() {
        return longReg;
    }

    public long getNumReg() {
        return numReg;
    }

    public long getNumRegMarcadosBorrado() {
        return numRegMarcadosBorrado;
    }

    // Recuperar datos de un registro
    public Map<String, String> recuperar(String valorClave) {
        int pos = 0;
        boolean encontrado = false;
        Map<String, String> result = null;

        try (FileInputStream fis = new FileInputStream(this.nombreFich)) {
            //result = null;
            // Recorremos todos los registros
            while (pos < this.numReg && !encontrado) {
                // Buffer -> Logitud del registro
                byte[] buffer = new byte[this.longReg];
                // Comprobamos la longitud de Bytes
                // Devuelve el numero de bytes leidos -> Tiene que ser igual que el longReg
                if (fis.read(buffer, 0, this.longReg) < this.longReg) {
                    return null;
                }

                int offsetCampo = 0;
                String unValorClave = null;

                for (Map.Entry<String, Integer> campo : campos.entrySet()) {
                    String unCampo = campo.getKey();
                    int longCampo = campo.getValue();

                    // Buscamos la PRIMARY KEY
                    if (unCampo.equals(this.campoClave)) {
                        unValorClave = new String(buffer, offsetCampo, longCampo, StandardCharsets.UTF_8);
                        break;
                    }
                    offsetCampo += longCampo;
                }
                if (valorClave.equals(unValorClave)) {
                    encontrado = true;
                    offsetCampo = 0;
                    result = new HashMap<String,String>();

                    for (Map.Entry<String, Integer> campo : campos.entrySet()) {
                        result.put(campo.getKey(), new String(buffer, offsetCampo, campo.getValue(), StandardCharsets.UTF_8));
                        offsetCampo += campo.getValue();
                    }
                }
                pos++;
            }

        } catch (IOException io) {
            System.out.println("ERROR E/S: " + io.getMessage());
        } finally {
            return result;
        }
    }

    public long insertar(HashMap<String,String> reg) throws IOException{
        String valorCampoClave = reg.get(this.campoClave);
        if (recuperar(valorCampoClave) != null){//Comprobamos si ya existe un registro con el mismo valor para el campo clave que el queremos insertar (No está permitido)
            return -1;
        }

        try(FileOutputStream fos = new FileOutputStream(nombreFich, true)){
            for (Map.Entry<String,Integer> campo: campos.entrySet()) {
                int longCampo = campo.getValue();
                String valorCampo = reg.get(campo.getKey());
                if (valorCampo == null){
                    valorCampo = "";
                }

                String valorCampoForm = String.format("%1$-" + longCampo + "s", valorCampo); //devuelve el valor del 1er argumento en un String con longitud "longCampo" y alineado a la izquierda (gracias al uso de "-")
                fos.write(valorCampoForm.getBytes("UTF-8"), 0, longCampo);
            }
        }catch (IOException e){
            System.out.println("Error de E/S: " + e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
        }
        this.numReg++;
        return this.numReg-1;
    }

}
