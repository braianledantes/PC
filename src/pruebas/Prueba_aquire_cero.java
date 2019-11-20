package pruebas;

import java.util.concurrent.Semaphore;

public class Prueba_aquire_cero {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(0);

        try {
            System.out.println("antes del aquiere");
            semaphore.acquire(0);
            System.out.println("despues del aquiere");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
