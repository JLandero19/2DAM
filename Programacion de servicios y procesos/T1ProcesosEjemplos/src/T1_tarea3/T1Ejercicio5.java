package T1_tarea3;

import java.io.File;
import java.io.IOException;

public class T1Ejercicio5 {
    public static void main(String[] args) throws IOException {

        if (args.length <= 0 || args.length > 1) {
            System.out.println("Debe indicarse el fichero por argumento del comando.");
            System.exit(1);
        }

        // Objeto Process
        Process p = null;
        // Instanciamos el objeto ProcessBuilder pasandole por parametro args
        ProcessBuilder pb = new ProcessBuilder("grep", "rc", "fichero.txt");
        pb.inheritIO();

        // COMPROBACION DE ERROR - 0 bien - 1 mal
        int exitVal;
        try {
            // Redirige la entrada de un fichero
            pb.redirectInput(new File("fichero.txt"));
            // Redirige la salida otro fichero
            pb.redirectOutput(new File(args[0]));
            // Redirige el error a otro fichero
            pb.redirectError(new File("error.log"));

            p = pb.start();

            // COMPROBACION DE ERROR - 0 bien - 1 mal
            exitVal = p.waitFor();
            System.out.println("Valor de Salida: " + exitVal);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
