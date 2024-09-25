import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Alumno {
    String nombre;
    String apellido;
    int edad;
    int curso;

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        ArrayList<Alumno> alumnos = new ArrayList();
        int num;

        alumnos.add(new Alumno("Javier", "Landero", 25, 2));
        alumnos.add(new Alumno("Antonio", "Rodriguez", 31, 2));

        do {
            System.out.println("Cuantos alumnos necesitas que aparezcan [max = " + alumnos.size() + "]");
            num = scanner.nextInt();
        } while (num > alumnos.size() || num <= 0);

        for (int i = 0; i < num; i++) {
            System.out.println(alumnos.get(i).toString());
        }
    }

    // Constructor
    public Alumno(String nombre, String apellidos, int edad, int curso) {
        this.nombre = nombre;
        this.apellido = apellidos;
        this.edad = edad;
        this.curso = curso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", curso=" + curso +
                '}';
    }
}
