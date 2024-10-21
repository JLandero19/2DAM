
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class T1Ejercicio8 {
    public static void main(String[] args) throws IOException {
        String nameFile = "entrada.txt";
        createFile(nameFile);
        showHash("entrada.txt");
    }

    public static void createFile(String nameFile) throws IOException {
        // Este comando de CMD recoge el resultado de dir y lo mete en el fichero
        String comando = "dir >" + nameFile;
        // Crea el proceso
        ProcessBuilder pb = new ProcessBuilder("CMD", "/C", comando);
        // Ejecuta el proceso
        Process p = pb.start();

        // Comprobación de problemas
        int exitVal;
        try {
            exitVal = p.waitFor();

            if (exitVal != 0) {
                System.out.println("Error en el proceso.");
            } else {
                System.out.println("Se ha ejecutado la creación de fichero exitosamente.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void showHash(String nameFile) throws IOException {
        // Este comando muestra el HASH de un fichero
        String comando = "Certutil -hashfile " + nameFile;
        // Crea el proceso
        ProcessBuilder pb = new ProcessBuilder("CMD", "/C", comando);
        // Ejecuta el proceso
        Process p = pb.start();

        // Utilizamos BufferedReader para leer el fichero
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        // Mostramos el contenido por línea mientras no sea nulo
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        // Comprobación de problemas
        int exitVal;
        try {
            exitVal = p.waitFor();

            if (exitVal != 0) {
                System.out.println("Error en el proceso.");
            } else {
                System.out.println("Se ha ejecutado el proceso exitosamente.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
