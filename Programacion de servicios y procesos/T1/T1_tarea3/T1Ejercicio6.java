import java.io.*;

public class T1Ejercicio6 {
    public static void main(String[] args) throws IOException {

        if (args.length <= 0 || args.length > 1) {
            System.out.println("Debe indicarse el fichero/directorio por argumento del comando.");
            System.exit(1);
        }

        File path = new File(args[0]);

        if (!path.exists()) {
            System.out.println("El directorio/archivo no existe");
            System.exit(1);
        }
        
        if (!path.isDirectory()) {
            System.out.println("El argumento introducido " + path.getName() + " es un fichero.");
            System.exit(1);
        }

        String command = "ls " + args[0];

        // Run the "dir" command on Windows
        Process p = Runtime.getRuntime().exec(command);

        // Read the output of the command
        InputStream is = p.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        int i = 1;
        while ((line = reader.readLine()) != null) {
            System.out.println(i + " " + line);
            i++;
        }

        // Wait for the command to complete and get the exit status
        try {
            int exitValue = p.waitFor();
            System.out.println("Exit value: " + exitValue);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
