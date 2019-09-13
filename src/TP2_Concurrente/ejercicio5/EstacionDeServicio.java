package TP2_Concurrente.ejercicio5;

import java.util.concurrent.Semaphore;

public class EstacionDeServicio extends Semaphore {
    public static final String ANSI_GREEN = "\u001B[32m";

    public EstacionDeServicio(int cantSurtidores) {
        super(cantSurtidores);
    }

    public EstacionDeServicio(int cantSurtidores, boolean fair) {
        super(cantSurtidores, fair);
    }

    public void surtir(Vehiculo v) {
        try {
            this.acquire();
            System.out.println(ANSI_GREEN + "Surtiendo a " + v.getName());
            Thread.sleep(1000);
            v.setConbustible(100);
            this.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
