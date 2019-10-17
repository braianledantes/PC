package Blancanieves_y_los_siete_enanitos;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int cant = 4, cantEnanos = 7;

        Random random = new Random(System.currentTimeMillis());
        Mesa mesa = new Mesa(cant);
        Blancanieves blancanieves = new Blancanieves(mesa, random);
        Enano[] enanos = new Enano[cantEnanos];

        for (int i = 0; i < enanos.length; i++) {
            enanos[i] = new Enano(mesa, "Enano" + i, random);
        }
        for (int i = 0; i <= enanos.length; i++) {
            if (i == enanos.length) {
                blancanieves.start();
            } else {
                enanos[i].start();
            }
        }
    }
}
