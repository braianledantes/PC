package Transbordador;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class TransbordadorCountDownLatch implements Transbordador {
    private final CountDownLatch ir = new CountDownLatch(10);
    private final CountDownLatch volver = new CountDownLatch(10);
    private final CountDownLatch bajar = new CountDownLatch(1);
    private final CountDownLatch subir = new CountDownLatch(1);

    // TODO no funciona, hay que buscar la manera de reiniciar los contadores (ir, volver, bajar, subir)

    @Override
    public void subir() {
        try {
            subir.await();
            System.out.println(Thread.currentThread().getName() + " subio al transbordador");
            ir.countDown();
        } catch (InterruptedException ignore) {
        }
    }

    @Override
    public void ir() {
        try {
            System.out.println(Thread.currentThread().getName() + " esperando coches");
            subir.countDown();
            ir.await();
            System.out.println(Thread.currentThread().getName() + " yendo...");
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + " llego");
            bajar.countDown();
        } catch (InterruptedException ignore) {
        }
    }

    @Override
    public void bajar() {
        try {
            bajar.await();
            System.out.println(Thread.currentThread().getName() + " bajo del transbordador");
            volver.countDown();
        } catch (InterruptedException ignore) {
        }
    }

    @Override
    public void volver() {
        try {
            volver.await();
            System.out.println(Thread.currentThread().getName() + " volviendo...");
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + " volvio");
        } catch (InterruptedException ignore) {
        }
    }
}
