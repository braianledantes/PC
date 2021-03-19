package Transbordador;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class TransbordadorCyclicBarrier implements Transbordador {
    CountDownLatch ir = new CountDownLatch(10);
    CountDownLatch volver = new CountDownLatch(10);
    CyclicBarrier subir = new CyclicBarrier(1);
    CyclicBarrier bajar = new CyclicBarrier(1);

    @Override
    public void subir() {
        try {
            subir.await();
            ir.countDown();
        } catch (InterruptedException | BrokenBarrierException ignore) {
        }
    }

    @Override
    public void ir() {
    }

    @Override
    public void bajar() {
    }

    @Override
    public void volver() {
    }
}
