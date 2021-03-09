package Transbordador;

import java.util.Random;

public class Coche extends Thread {
    Transbordador transbordador;
    Random random = new Random();

    public Coche(String name, Transbordador transbordador) {
        super(name);
        this.transbordador = transbordador;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(random.nextInt(3000) + 1000);
            transbordador.subir();
            transbordador.bajar();
        } catch (InterruptedException ignored) {
        }
    }
}
