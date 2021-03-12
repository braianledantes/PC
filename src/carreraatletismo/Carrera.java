package carreraatletismo;

import java.util.concurrent.Semaphore;

public class Carrera {
    private final Semaphore testigo = new Semaphore(1, true);

    public void esperarTestigo() {
        System.out.println(Thread.currentThread().getName() + " esperando");
        try {
            testigo.acquire();
        } catch (InterruptedException ignore) {
        }
    }

    public void correr() {
        try {
            long tipempoIni = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + " corriendo...");

            long tiempo = (long) (Math.random() * (11000 - 9000 + 1)) + 9000L;
            Thread.sleep(tiempo);
            long tipempoFin = System.currentTimeMillis();

            long tiempoCarrera = tipempoFin - tipempoIni;
            System.out.println(Thread.currentThread().getName() + " llego en " + tiempoCarrera + " milisegundos");
        } catch (InterruptedException ignore) {
        }
    }

    public void entregrarTestigo() {
        testigo.release();
    }
}
