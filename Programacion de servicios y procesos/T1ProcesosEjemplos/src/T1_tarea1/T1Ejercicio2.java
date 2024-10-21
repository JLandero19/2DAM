package T1_tarea1;

import java.io.IOException;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class T1Ejercicio2 {
    public static void main(String[] args) throws InterruptedException {
        try {
            // Crear un nuevo proceso
            ProcessBuilder processBuilder = new ProcessBuilder("./ejemplo.sh");
            Process process = processBuilder.start();
            // Mientras el proceso siga vivo cada 3 segundos muestra un mensaje
            while (process.isAlive()) {
                System.out.println(currentThread() + " sigue en ejecución");
                Thread.sleep(3000);
            }
            process.waitFor();
        } catch (IOException e) {
            System.err.println("Error al iniciar el proceso: " + e.getMessage());
        }
    }
}
