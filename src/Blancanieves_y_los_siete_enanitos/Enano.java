package Blancanieves_y_los_siete_enanitos;

import colores.ColoresString;

import java.util.Random;

public class Enano extends Thread {
    private Mesa mesa;
    private String nombre;
    private Random random;

    public Enano(Mesa mesa, String nombre, Random random) {
        this.mesa = mesa;
        this.nombre = nombre;
        this.random = random;
    }

    @Override
    public void run() {
        while (true) {
            comer();
            trabajar();
        }
    }

    public void comer() {
        mesa.entrarAMesa();
        System.out.println(ColoresString.ANSI_GREEN + nombre + " comiendo");
        try {
            Thread.sleep(1000 + random.nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mesa.salirDeMesa();
        System.out.println(ColoresString.ANSI_GREEN + nombre + " termino de comer.");
    }

    public void trabajar() {
        try {
            System.out.println(ColoresString.ANSI_RED + nombre + " trabajando.");
            Thread.sleep(1000 + random.nextInt(3000));
            System.out.println(ColoresString.ANSI_RED + nombre + " dejo de trabajar.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
