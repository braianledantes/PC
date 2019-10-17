package Blancanieves_y_los_siete_enanitos;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Mesa {
    private Semaphore lugares, esperandoParaComer;
    private int cantLugares;

    public Mesa(int cantLugares) {
        try {
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

    public void salirDeMesa() {
        lugares.release();
    }


    public void servirComida() {
        if (lugares.availablePermits() < cantLugares) {
            esperandoParaComer.release(cantLugares - lugares.availablePermits());
        }
    }
}
