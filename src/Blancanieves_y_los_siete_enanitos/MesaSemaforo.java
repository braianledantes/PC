package Blancanieves_y_los_siete_enanitos;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class MesaSemaforo implements Mesa {
    private Semaphore lugares, esperandoParaComer;
    private int cantLugares;
    private Random random;

    public MesaSemaforo(int cantLugares) {
        try {
            this.random = new Random();
            this.cantLugares = cantLugares;
            this.lugares = new Semaphore(cantLugares);
            this.esperandoParaComer = new Semaphore(cantLugares);
            this.esperandoParaComer.acquire(cantLugares);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void entrarAMesa() {
        try {
            lugares.acquire();
            esperandoParaComer.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void comer() {
        try {
            Thread.sleep(1000 + random.nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void salirDeMesa() {
        lugares.release();
    }


    public void servirComida() {
        if (lugares.availablePermits() < cantLugares) {
            esperandoParaComer.release(cantLugares - lugares.availablePermits());
        }
    }
}
