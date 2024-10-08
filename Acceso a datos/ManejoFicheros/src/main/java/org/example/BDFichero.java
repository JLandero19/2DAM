package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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
            // f.length() -> sacamos lo que ocupa el fichero en total
            // f.length() / longReg -> conseguimos la cantidad de registros que hay en el fichero
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
                // unValorClave -> si el PRIMARY KEY que sacamos del fichero tiene espacios el .equals lo considera diferente
                // por lo tanto para evitar ese problema le quitamos los espacios que queden por detrás con .trim()
                if (valorClave.equals(unValorClave.trim())) {
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

    public long insertar(HashMap<String,String> reg) throws IOException {
        String valorCampoClave = reg.get(this.campoClave);
        if (recuperar(valorCampoClave) != null){//Comprobamos si ya existe un registro con el mismo valor para el campo clave que el queremos insertar (No está permitido)
            System.err.println("No se puede insertar el registro debido a que ya existe uno con esta clave primaria: " + valorCampoClave);
            return -1;
        }

        try(FileOutputStream fos = new FileOutputStream(nombreFich, true)){
            for (Map.Entry<String,Integer> campo: campos.entrySet()) {
                int longCampo = campo.getValue();
                String valorCampo = reg.get(campo.getKey());
                if (valorCampo == null){
                    valorCampo = "";
                }
                // %1$ -> el primer parametro que le pasamos
                // - -> alineación a la izquierda
                // longCampo -> ejemplo 32, tiene que rellenar con espacio hasta 32 bytes
                // s -> indica que es String
                String valorCampoForm = String.format("%1$-" + longCampo + "s", valorCampo); //devuelve el valor del 1er argumento en un String con longitud "longCampo" y alineado a la izquierda (gracias al uso de "-")
                // Se formatea con UTF-8
                // El offset es 0, porque [valorCampoForm] está rellenando los campos con espacios
                // longCampo -> en este caso necesita la longitud del campo
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

    public boolean modificar(String valorClave, String nombreCampo, String valorCampo) throws  IOException{
        if(nombreCampo.equals(this.campoClave)){
            System.out.println("No se puede modificar el campo clave: " + nombreCampo);
            return false;
        }
        int pos = 0;
        boolean encontrado = false;
        RandomAccessFile raf = new RandomAccessFile(this.nombreFich, "rws");
        while(pos < this.numReg && !encontrado){
            byte buffer[] = new byte[this.longReg];
            if (raf.read(buffer, 0, this.longReg) < this.longReg){
                return false;
            }
            String unValorClave = recuperarValorCampoClave(buffer);

            if(valorClave.equals(unValorClave.trim())){
                int offsetCampo = 0;
                encontrado = true;
                raf.seek(pos*longReg);
                for(Map.Entry<String,Integer> campo: campos.entrySet()){
                    String unCampo = campo.getKey();
                    int longCampo = campo.getValue();
                    if(nombreCampo.equals(unCampo)){
                        raf.skipBytes(offsetCampo);
                        String valorCampoForm = String.format("%1$-" + longCampo + "s", valorCampo);
                        raf.write(valorCampoForm.getBytes("UTF-8"), 0, longCampo);
                        break;
                    }
                    offsetCampo += longCampo;
                }
            }
            pos++;
        }
        return encontrado;

    }

    /**
     * Borrar registro, identificado por valor de campo clave. Realmente no se
     * borra, sino que se marca como borrado, poniendo todos los bytes a cero.
     *
     * @param valorClave Valor del campo clave para localizar el registro
     * @return true si se ha encontrado y borrado el registro, false en otro
     * caso.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public boolean borrar(String valorClave) throws FileNotFoundException, IOException {
        // Muy parecido a la búsqueda y modificación, con la diferencia de que, una vez encontrado,
        // se marca como borrado
        int pos = 0;
        boolean encontrado = false;
        try (RandomAccessFile raf = new RandomAccessFile(this.nombreFich, "rws")) {  // Se necesita leer y escribir, y además volver hacia atrás a una posición conocida
            while (pos < this.numReg && !encontrado) {
                byte buffer[] = new byte[this.longReg]; // Leer registro
                if (raf.read(buffer, 0, this.longReg) < this.longReg) {
                    return false;
                }
                int offsetCampo = 0;  // Obtener valor del campo clave
                String unValorClave = null;
                for (Map.Entry<String,Integer> campo : campos.entrySet()) {
                    String unCampo = campo.getKey();
                    int longCampo = campo.getValue();
                    if (unCampo.equals(this.campoClave)) {
                        unValorClave = new String(buffer, offsetCampo, longCampo, StandardCharsets.UTF_8);
                        break;  // Ya tenemos el valor del campo clave
                    }
                    offsetCampo += longCampo;
                }
                if (valorClave.equals(unValorClave)) {
                    offsetCampo = 0;
                    encontrado = true;  // Ahora hay que poner todos los bytes a cero en registro en posición pos
                    raf.seek(pos * this.longReg);
                    Arrays.fill(buffer, (byte) 0);  // Por si acaso, no es necesario.
                    raf.write(buffer, 0, this.longReg);
                    this.numRegMarcadosBorrado++;
                }
                pos++;
            }
        }
        return encontrado;
    }

    /**
     * Compacta fichero, es decir, elimina registros marcados como borrados
     *
     * @return número de registros marcados como borrado, que se han eliminado.
     * -1 si ha habido algún error que ha impedido compactar el fichero.
     * @throws IOException
     */
    public int compactar() throws IOException {
        int numSuprimidos = 0;
        int numReg_ = 0;
        File fTemp = File.createTempFile(nombreFich, "");
        try (FileInputStream fis = new FileInputStream(nombreFich);
             FileOutputStream fos = new FileOutputStream(fTemp)) {
            byte[] buffer = new byte[this.longReg];
            for (int pos = 0; pos < this.numReg; pos++) {
                fis.read(buffer);
                boolean noCero = false;  // Ver si marcado para borrado: todo a cero
                for (int j = 0; j < this.longReg && !noCero; j++) {
                    if (buffer[j] != 0) {
                        noCero = true;
                    }
                }
                if (noCero) {
                    fos.write(buffer);
                    numReg_++;
                } else {
                    numSuprimidos++;
                }
            }
        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Copia de seguridad de fichero original, con timestamp y número aleatorio.
        // Necesario para poder renombrar fichero temporal como original. Podría borrarse al final.
        java.util.Random r = new java.util.Random();
        String nombreCopiaSeg = nombreFich + "." + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "." + r.nextInt() + ".bak";
        File fOrig = new File(nombreFich);
        if (!fOrig.renameTo(new File(nombreCopiaSeg))) {
            System.err.println("Error haciendo copia de seguridad de " + nombreFich + " a " + nombreCopiaSeg);
            return -1;
        }
        String nombreFichTemp = fTemp.getAbsolutePath(); // Se renombra fichero temporal como original
        if (!fTemp.renameTo(new File(nombreFich))) {
            System.err.println("Error copiando de fichero temporal " + nombreFichTemp + " a " + nombreFich);
            return -1;
        }
        //File fCopiaSeg=new File(nombreCopiaSeg); fCopiaSeg.delete();  // descomentar para borrar copia de seguridad

        this.numReg = numReg_;
        this.numRegMarcadosBorrado = 0;
        return numSuprimidos;
    }

    private String recuperarValorCampoClave(byte[] buffer) {
        int offsetCampo = 0;
        String unValorClave = null;
        for (Map.Entry<String, Integer> campo : campos.entrySet()) {
            String unCampo = campo.getKey();
            int longCampo = campo.getValue();
            if (unCampo.trim().equals(this.campoClave)) {
                unValorClave = new String(buffer, offsetCampo, longCampo, StandardCharsets.UTF_8);
                break;
            }
            offsetCampo += longCampo;
        }
        return unValorClave;
    }

}
