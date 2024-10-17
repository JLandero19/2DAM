import java.io.IOException;

public class Ejemplo1 {
   public static void main(String[] args) throws IOException  {
       // Crea el proceso
	   ProcessBuilder pb = new ProcessBuilder("NOTEPAD");
       // Ejecuta el proceso
	   Process p = pb.start();

   }
}//Ejemplo1

