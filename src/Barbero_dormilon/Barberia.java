package Barbero_dormilon;

import colores.ColoresString;

public class Barberia {
    private int sillasDisponibles;
    private int sillonOcupado;

    public Barberia(int sillas) {
        this.sillasDisponibles = sillas;
        this.sillonOcupado = -1;
    }

    public synchronized void entrar(int id) {
        // System.out.println(ColoresString.ANSI_YELLOW + "cliente " + id + " esperando");
        while (sillasDisponibles == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        sillasDisponibles--;
        System.out.println(ColoresString.ANSI_YELLOW + "cliente " + id + " se sienta");
    }

    public synchronized void sentarseEnSillon(int id) {
        while (sillonOcupado != -1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        sillasDisponibles++;
        sillonOcupado = id;
        System.out.println(ColoresString.ANSI_RED + "cliente " + id + " en sillon");
        notifyAll();
    }

    public void afeitar() throws InterruptedException {
        synchronized (this) {
            while (sillonOcupado == -1) {
                wait();
            }
            System.out.println(ColoresString.ANSI_BLUE + "Afeitando al cliente " + sillonOcupado);
        }
        Thread.sleep(2000);
        synchronized (this) {
            sillonOcupado = -1;
            notifyAll();
        }
    }
}
