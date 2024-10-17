import java.io.*;

public class Ejemplo2 {
	public static void main(String[] args) throws IOException {
		// Crea el proceso y lo ejecuta con el .start()
		Process p = new ProcessBuilder("ls","-l").start();
		try {
			// Crea un objeto de InputStream
			// .getInputstream() lo que hace es recoger la salida de Proceso [variable -> Process p]
			InputStream is = p.getInputStream();

			// Mostramos en pantalla caracter a caracter
			int c;
			while ((c = is.read()) != -1)
				System.out.print((char) c);

			// Cierra el InputStream
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
	}
}// Ejemplo2
