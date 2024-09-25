public class Ejercicio1Practico {
    private int min = 1;
    private int max = 100;

    public void ejercicio1() {
        for (int i = this.min; i <= this.max; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        Ejercicio1Practico e = new Ejercicio1Practico();
        e.ejercicio1();
    }
}
