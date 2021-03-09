package Transbordador;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class TransbordadorCyclicBarrier implements Transbordador {
    CyclicBarrier ir = new CyclicBarrier(10);
    CyclicBarrier volver = new CyclicBarrier(10);
    CountDownLatch subir = new CountDownLatch(10);

    @Override
    public void subir() {
        // TODO
    }

    @Override
    public void ir() {
        // TODO
    }

    @Override
    public void bajar() {
        // TODO
    }

    @Override
    public void volver() {
        // TODO
    }
}
