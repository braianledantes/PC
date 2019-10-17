package Blancanieves_y_los_siete_enanitos;

import java.util.Random;
import colores.ColoresString;

public class Blancanieves extends Thread {
    private Mesa mesa;
    private Random random;

    public Blancanieves(Mesa mesa, Random random) {
        this.mesa = mesa;
        this.random = random;
    }

    @Override
    public void run() {
        while (true) {
            pasearConPrincipe();
            servirComida();
        }
    }

    public void servirComida() {
        System.out.println(ColoresString.ANSI_YELLOW + "Princesa sirviendo comida");
        mesa.servirComida();
    }

    public void pasearConPrincipe() {
        try {
            System.out.println(ColoresString.ANSI_YELLOW + "Princesa paseando con su amigo el principe Encantador");
            Thread.sleep(1000 + random.nextInt(6000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
