import java.io.File;
import java.io.IOException;

public class Ejemplo8_2 {
	public static void main(String args[]) throws IOException {
		ProcessBuilder pb = new ProcessBuilder("CMD");	
			
		File fBat = new File("fichero.bat");
	    File fOut = new File("salida.txt");
	    File fErr = new File("error.txt");
	    
		pb.redirectInput(ProcessBuilder.Redirect.from(fBat));
		pb.redirectOutput(ProcessBuilder.Redirect.to(fOut));
		pb.redirectError(ProcessBuilder.Redirect.to(fErr)); 
		    
		Process p = pb.start();
	}
}// EjemploPB2
