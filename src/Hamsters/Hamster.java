package Hamsters;

import java.util.concurrent.Semaphore;

public class Hamster extends Thread {
    Jaula jaula;

    public Hamster(Jaula jaula) {
        this.jaula = jaula;
    }

    @Override
    public void run() {
        while (true) {
            jaula.comer();
            jaula.rodar();
            jaula.dormir();
        }
    }

    public static void main(String[] args) {
        int CANT_AMSTERS = 10;
        Hamster[] hamsters = new Hamster[CANT_AMSTERS];
        Jaula jaula = new Jaula();
        for (int i = 0; i < hamsters.length; i++) {
            hamsters[i] = new Hamster(jaula);
            hamsters[i].start();
        }
//        for (Hamster h : hamsters) {
//            h = new Hamster(jaula);
//            System.out.println("acaaaa");
//           // h.start();
//        }
        try {
            for (Hamster h : hamsters) {
                //System.out.println(h);
                h.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Jaula {
    Plato plato;
    Rueda rueda;
    Hamaca hamaca;

    public Jaula() {
        this.plato = new Plato(3);
        this.rueda = new Rueda(1);
        this.hamaca = new Hamaca(1);
    }

    public void comer() {
        try {
            plato.acquire();
            System.out.println(Thread.currentThread().getName() + ": COMIENDO");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + ": TERMINO DE COMER");
            plato.release();
        }
    }

    public void rodar() {
        try {
            rueda.acquire();
            System.out.println(Thread.currentThread().getName() + ": RODANDO");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + ": TERMINO DE RODAR");
            rueda.release();
        }
    }

    public void dormir() {
        try {
            hamaca.acquire();
            System.out.println(Thread.currentThread().getName() + ": DURMIENDO");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + ": DESPERTO");
            hamaca.release();
        }
    }
}

class Plato extends Semaphore {

    public Plato(int permits) {
        super(permits);
    }

    public Plato(int permits, boolean fair) {
        super(permits, fair);
    }
}

class Rueda extends Semaphore {

    public Rueda(int permits) {
        super(permits);
    }

    public Rueda(int permits, boolean fair) {
        super(permits, fair);
    }
}

class Hamaca extends Semaphore {

    public Hamaca(int permits) {
        super(permits);
    }

    public Hamaca(int permits, boolean fair) {
        super(permits, fair);
    }
}