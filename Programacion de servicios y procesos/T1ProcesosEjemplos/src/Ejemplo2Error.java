import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Ejemplo2Error {
    public static void main(String[] args) throws IOException {
        // Es un ejemplo de ERROR con un comando mal escrito
        Process p = new ProcessBuilder("CMD", "/C", "DIRR").start();
        try {

            InputStream is = p.getInputStream();

            // Mostramos en pantalla caracter a caracter
            int c;
            while ((c = is.read()) != -1)
                System.out.print((char) c);
            is.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        // COMPROBACION DE ERROR - 0 bien - 1 mal
        int exitVal;
        try {
            exitVal = p.waitFor();
            System.out.println("Valor de Salida: " + exitVal);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            // Crea un ErrorStream
            InputStream er = p.getErrorStream();
            // Realiza las modificaciones de una clase a otra
            BufferedReader brer = new BufferedReader(new InputStreamReader(er));
            String liner = null;
            while ((liner = brer.readLine()) != null)
                System.out.println("ERROR > " + liner);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }
}// Ejemplo2

