package Barbero_dormilon;

import colores.ColoresString;

public class Barbero implements Runnable {
    private Barberia barberia;

    public Barbero(Barberia barberia) {
        this.barberia = barberia;
    }

    @Override
    public void run() {
        while (true) {
            try {
                barberia.afeitar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
