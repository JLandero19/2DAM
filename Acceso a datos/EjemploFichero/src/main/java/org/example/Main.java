package org.example;

import java.io.File;
import java.text.SimpleDateFormat;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String ruta = ".";
        if (args.length >= 1) {
            ruta = args[0];
        }

        File fich = new File(ruta);

        if (!fich.exists()) {
            System.out.println("No existe el fichero o directorio (" + ruta + ")");
        }else{
            if(fich.isFile()){
                System.out.println("El elemento " + ruta + " es un fichero");
                System.out.println("El fichero ocupa " + fich.length() + " bytes");
                // System.out.println(getDateString(fich.lastModified()) );
            }else{
                System.out.println(ruta + " es un directorio. Contenidos:");
                File[] ficheros = fich.listFiles();
                for(File f: ficheros) {
                    String textoDescr = f.isDirectory() ? "/" : f.isFile() ? "_" : "?";
                    System.out.println("(" + textoDescr + ") " + f.getName());
                }
            }
        }
    }
    private static String getDateString(long timeInMilliseconds){
        SimpleDateFormat formatter = new SimpleDateFormat("d MMM yyyy 'at' HH:mm:ss z");
        return formatter.format(timeInMilliseconds);
    }

}