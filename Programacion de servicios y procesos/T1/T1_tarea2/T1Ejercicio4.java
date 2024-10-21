import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class T1Ejercicio4 {
    public static void main(String[] args) throws IOException {

        // Objeto Process
        Process p = null;
        // Instanciamos el objeto ProcessBuilder pasandole por parametro args
        ProcessBuilder pb = new ProcessBuilder("ls", "-l");
        pb.inheritIO();

        try {
            // Lo ejecutamos
            p = pb.start();

            // Recupera y modifica el entorno del proceso
            Map<String, String> environment = pb.environment();

            for(Map.Entry<String,String> it: environment.entrySet()) {
                System.out.println("Clave: " + it.getKey() + " - Valor: " + it.getValue());
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
