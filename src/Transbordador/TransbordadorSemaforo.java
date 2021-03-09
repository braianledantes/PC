package Transbordador;

import java.util.concurrent.Semaphore;

public class TransbordadorSemaforo implements Transbordador {
   // Semaphore entrada = new Semaphore(1);
    Semaphore subir = new Semaphore(10);
    Semaphore bajar = new Semaphore(0);
    Semaphore viajar = new Semaphore(0);

    @Override
    public void subir() {
        try {
            //System.out.println(Thread.currentThread().getName() + " esperando al transbordador");
            subir.acquire();
            viajar.release();
            System.out.println(Thread.currentThread().getName() + " subio al transbordador");
        } catch (InterruptedException ignored) {
        }
    }

    @Override
    public void ir() {
        try {
            System.out.println(Thread.currentThread().getName() + " esperando coches");
            viajar.acquire(10);
            System.out.println(Thread.currentThread().getName() + " yendo...");
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + " llego");
            bajar.release(10);
        } catch (InterruptedException ignored) {
        }
    }

    @Override
    public void bajar() {
        try {
            bajar.acquire();
            viajar.release();
            System.out.println(Thread.currentThread().getName() + " bajo del transbordador");
        } catch (InterruptedException ignored) {
        }
    }

    @Override
    public void volver() {
        try {
            viajar.acquire(10);
            System.out.println(Thread.currentThread().getName() + " volviendo...");
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + " volvio");
            subir.release(10);
        } catch (InterruptedException ignored) {
        }
    }
}
