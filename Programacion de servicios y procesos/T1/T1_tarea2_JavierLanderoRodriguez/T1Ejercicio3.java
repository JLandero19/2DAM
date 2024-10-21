import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class T1Ejercicio3 {
    public static void main(String[] args) throws IOException {

        if (args.length <= 0) {
            System.out.println("Debe indicarse comando a ejecutar.");
            System.exit(1);
        }
        // Objeto Process
        Process p = null;
        // Instanciamos el objeto ProcessBuilder pasandole por parametro args
        ProcessBuilder pb = new ProcessBuilder(args);
        pb.inheritIO();

        try {
            // Lo ejecutamos
            p = pb.start();

            // Esto es para mostrar el directorio de trabajo
            if (args.length > 1) {
                if (args.length > 2) {
                    pb.directory(new File(args[2]));
                } else {
                    pb.directory(new File(args[1]));
                }
                System.out.println(pb.directory().getCanonicalPath());
            } else if (args.length == 1) {
                pb.directory(new File("./"));
                System.out.println(pb.directory().getCanonicalPath());
            }


            int codRet = p.waitFor();
            System.out.println("La ejecución de " + Arrays.toString(args)
                    + " devuelve " + codRet
                    + " " + (codRet == 0 ? "(ejecución correcta)" : "(ERROR)")
            );
        } catch (IOException e) {
            System.err.println("Error durante ejecución del proceso");
            System.err.println("Información detallada");
            System.err.println("---------------------");
            e.printStackTrace();
            System.err.println("---------------------");
            System.exit(2);
        } catch (InterruptedException e) {
            System.err.println("Proceso interrumpido");
            System.exit(3);
        }

        // COMPROBACION DE ERROR - 0 bien - 1 mal
        int exitVal;
        try {
            exitVal = p.waitFor();
            System.out.println("Valor de Salida: " + exitVal);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
