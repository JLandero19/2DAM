import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class T1Ejercicio2 {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 10; i++) {
            System.out.println(i);
            if (currentThread().isAlive()) {
                System.out.println(currentThread() + " sigue en ejecución");
            }
            sleep(3000);
        }
    }
}
