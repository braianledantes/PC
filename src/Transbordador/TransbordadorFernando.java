package Transbordador;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TransbordadorFernando {
    private int cantidad;
    private CyclicBarrier barrera;

    public TransbordadorFernando(int cantidad) {
        this.cantidad = cantidad;
        this.barrera = new CyclicBarrier(cantidad, new Runnable() {
            @Override
            public void run() {
                salir();
            }
        });
    }

    private void salir() {
        // usado por el transbordador
    }

    public void subir() {
        try {
            System.out.println("esperando al");
            barrera.await();
        } catch (InterruptedException e) {
        } catch (BrokenBarrierException e) {
        }
    }
}
