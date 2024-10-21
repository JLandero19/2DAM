
import java.io.IOException;

public class T1Ejercicio7 {
    public static void main(String[] args) throws IOException {

        String comando = "start POWERSHELL";
        // Crea el proceso
        ProcessBuilder pb = new ProcessBuilder("CMD", "/C", comando);
        // Ejecuta el proceso
        Process p = pb.start();

        int exitVal;
        try {
            exitVal = p.waitFor();

            if (exitVal != 0) {
                System.out.println("Error en el proceso.");
            } else {
                System.out.println("Se ha ejecutado el programa exitosamente.");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
