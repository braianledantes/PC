package Transbordador;

public class TransbordadorMonitor implements Transbordador {
    int cantCochesArriba = 0;
    boolean subir = true;
    boolean bajar = false;

    @Override
    public synchronized void subir() {
        try {
            while (!subir || this.cantCochesArriba >= 10) {
                this.wait();
            }
            this.cantCochesArriba++;
            System.out.println(Thread.currentThread().getName() + " subio al transbordador");
            this.notifyAll();
        } catch (InterruptedException ignored) {
        }
    }

    @Override
    public synchronized void ir() {
        try {
            System.out.println(Thread.currentThread().getName() + " esperando coches");
            while (cantCochesArriba < 10) {
                this.wait();
            }
            this.subir = false;

            System.out.println(Thread.currentThread().getName() + " yendo...");
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + " llego");
            this.bajar = true;
            this.notifyAll();

        } catch (InterruptedException ignored) {
        }
    }

    @Override
    public synchronized void bajar() {
        try {
            while (!bajar) {
                this.wait();
            }
            this.cantCochesArriba--;
            System.out.println(Thread.currentThread().getName() + " bajo del transbordador");
            this.notifyAll();
        } catch (InterruptedException ignored) {
        }
    }

    @Override
    public synchronized void volver() {
        try {
            while (cantCochesArriba > 0) {
                this.wait();
            }
            this.bajar = false;

            System.out.println(Thread.currentThread().getName() + " volviendo...");
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + " volvio");

            this.subir = true;
            this.notifyAll();

        } catch (InterruptedException ignored) {
        }
    }
}
