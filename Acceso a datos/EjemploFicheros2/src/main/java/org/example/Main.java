package org.example;

import java.io.*;
import java.text.SimpleDateFormat;

public class Main {

    public static void main(String[] args) {
        String route = ".";
        if (args.length < 1) {
            System.out.println("Necesitas introducir un parámetro de entrada");
            return;
        }

        String nameFile = args[0];

        // exampleWrite1(nameFile);
        // exampleWrite2(nameFile);

        // exampleRead2(nameFile);

        // Creamos el archivo volcado
        String fileDump = (new File(nameFile).getName()) + ".dump.txt";
        // Necesita hacer un try-catch para prevenir errores
        try {
            exampleDump(new PrintStream(fileDump), nameFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    // Trabajamos con el Buffer de Bytes exampleDump = volcado
    private static void exampleDump(PrintStream ps, String nameFile) {
        // FileInputStream es para crear el flujo
        // PrintStream ps -> recoge ps.println() o ps.format()
        try (FileInputStream fis = new FileInputStream(new File(nameFile))){
            // Buffer de lectura de [32 bytes]
            byte[] buffer = new byte[32];
            // Aguí guardaremos los bytes leidos por el buffer
            int bytesRead;
            // Desde donde indique el offset
            int offset = 0;

            // En cada iteracción del bucle se leen [32 bytes]
            do {
                // bytesRead -> se añaden los bytes leidos
                bytesRead = fis.read(buffer);

                // Imprime el valor de desplazamiento offset [0, 32, 64 ...]
                // Formato de 5 digitos
                ps.format("[%5d] ", offset);

                // Bucle para mostrar los bytes
                for (int i = 0; i < bytesRead; i++) {
                    // Formato Hexadecimal
                    ps.format(" %2x", buffer[i]);
                }
                // Actualizamos la posicion sumandole bytesRead [32 bytes]
                offset += bytesRead;
                // Salto de línea
                ps.println();
                // El tope de bytes para leer en este caso son 2048 bytes
                // Para hacerlo de forma correcta bytesRead y maxOffset deberian ser constante
            } while (bytesRead == 32 && offset < 2048);
        } catch (IOException io) {
            System.out.println("Error en la E/S: " + io.getMessage());
        }
    }

    // Lee por caracter
    private static void exampleRead1(String nameFile) {
        try{
            FileReader fr = new FileReader(nameFile);
            int caract = fr.read();
            while(caract != -1){//Ha llegado al final del fichero
                System.out.print((char)caract);
                caract = fr.read();
            }
            fr.close();
            //fr.read();//Para probar la Excepción IOException
        }catch (FileNotFoundException fnf){
            System.out.println(" No existe el fichero: " + nameFile);
        }catch (IOException io){
            System.out.println("Error en la E/S: " + io.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void exampleRead2(String nameFile) {
        try{
            BufferedReader fbr = new BufferedReader(new FileReader(nameFile));
            int i = 0;
            String linea = fbr.readLine();
            while (linea != null){
                // %d -> seria el número de linea
                // %s -> sería el contenido de la linea
                System.out.format("[%d] %s", ++i, linea);
                System.out.println();
                linea = fbr.readLine();
            }
            fbr.close();
        }catch (FileNotFoundException fnf){
            System.out.println(" No existe el fichero: " + nameFile);
        }catch (IOException io){
            System.out.println("Error en la E/S: " + io.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Mirar el ejemplo de Moodle que es diferente
    private static void exampleRead3(String nameFile) {
        try {
            // Entrada para leer el archivo
            BufferedReader fr = new BufferedReader(new FileReader(nameFile));

            // readLine() -> solo lee la primera linea
            String lineString = fr.readLine();

            // Para mostrar el archivo completo utilizamos un bucle while
            while (lineString != null) {
                // Muestra la línea si no es null
                System.out.println(lineString);

                // Lee la siguiente linea
                lineString = fr.readLine();
            }

            // Cierra el archivo
            fr.close();

        } catch (FileNotFoundException fnf) {
            System.out.println("No existe el fichero: " + nameFile);
        } catch (IOException io) {
            // Lo envia por consola
            //io.printStackTrace(System.out);
            System.out.println("Error en la E/S: " + io.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void exampleWrite1(String nameFile) {
        // try with resource -> el recurso se cierra automáticamente
        // new FileWriter(nameFile, true) -> el true es para que se abra el archivo en modo añadir texto (no sobreescribe)
        try (FileWriter fw = new FileWriter(nameFile, true)) {
            fw.write(" ");
            fw.write("H");
            fw.write("o");
            fw.write("l");
            fw.write("a");

        } catch (IOException io) {
            System.out.println("Error en la E/S: " + io.getMessage());
        }
    }

    private static void exampleWrite2(String nameFile) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(nameFile));

            // Escribimos en el fichero
            bw.write("Esto es un fichero de texto");

            // Pone el cursor en la siguiente linea
            bw.newLine();
            bw.write("Estamos construyendo con un flujo con Buffer");
            bw.newLine();

            // Cerramos el archivo -> hay que hacerlo siempre porque sino no se guarda lo que has escrito
            bw.close();

            // Hay que ponerlo en modo Append si queremos añadir y no sobreescribir
            bw = new BufferedWriter(new FileWriter(nameFile, true));

            bw.write("Esta cadena puede machacar lo anterior");
            bw.newLine();
            bw.close();


        } catch (IOException io) {
            System.out.println("Error en la E/S: " + io.getMessage());
        }
    }

    // Comprueba si es un fichero o directorio
    private static void exampleComprobateFile(String ruta){
        File fich = new File(ruta);

        if (!fich.exists()) {
            System.out.println("No existe el fichero o directorio (" + ruta + ")");
        }else{
            if(fich.isFile()){
                System.out.println("El elemento " + ruta + " es un fichero");
                System.out.println("El fichero ocupa " + fich.length() + " bytes");
                // System.out.println(getDateString(fich.lastModified()) );
            }else{
                System.out.println(ruta + " es un directorio. Contenidos:");
                File[] ficheros = fich.listFiles();
                for(File f: ficheros) {
                    String textoDescr = f.isDirectory() ? "/" : f.isFile() ? "_" : "?";
                    System.out.println("(" + textoDescr + ") " + f.getName());
                }
            }
        }
    }

    private static String getDateString(long timeInMilliseconds){
        SimpleDateFormat formatter = new SimpleDateFormat("d MMM yyyy 'at' HH:mm:ss z");
        return formatter.format(timeInMilliseconds);
    }
}