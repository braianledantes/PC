package Filosofos;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Mesa {

    private int cant;
    private Semaphore[] tenedores;
    private Random random;

    public Mesa(int cant) {
        this.cant = cant;
        random = new Random(System.currentTimeMillis());
        tenedores = new Semaphore[cant];

        for (int i = 0; i < cant; i++) {
            tenedores[i] = new Semaphore(1, false);
        }
    }

    public void comer(Filosofo filosofo) {
        Semaphore tenedor1 = tenedores[filosofo.getPos()];
        Semaphore tenedor2 = tenedores[(filosofo.getPos() + 1) % cant];

        boolean tomo1 = tenedor1.tryAcquire();
        boolean tomo2 = tenedor2.tryAcquire();

        if ((tomo1 && !tomo2) || (!tomo1 && tomo2)) {
            try {
                System.out.println("\u001B[34m" + filosofo.getName() + " tengo un TENEDOR °°°");
                Thread.sleep(1000 * (1 + random.nextInt(2)));
                if (tomo1) tomo2 = tenedor2.tryAcquire();
                if (tomo2) tomo1 = tenedor1.tryAcquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (tomo1 && tomo2) {
            try {
                System.out.println("\u001B[31m" + filosofo.getName() + " estoy COMIENDO <----");
                Thread.sleep(1000 * (1 + random.nextInt(3)));
                System.out.println("\u001B[0m" + filosofo.getName() + " DEJÉ DE COMER  ---->");
                tenedor1.release();
                tenedor2.release();
                //Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            if (tomo1) {
                tenedor1.release();
            }
            if (tomo2) {
                tenedor2.release();
            }
        }
    }

    public void comer2(Filosofo filosofo) {
        Semaphore tenedor1 = tenedores[filosofo.getPos()];
        Semaphore tenedor2 = tenedores[(filosofo.getPos() + 1) % cant];
        boolean tomo1 = tenedor1.tryAcquire();
        boolean tomo2 = tenedor2.tryAcquire();

        if (tomo1 && tomo2) {
            try {
                System.out.println("\u001B[31m" + filosofo.getName() + " estoy COMIENDO <----");
                Thread.sleep(1000 * (1 + random.nextInt(3)));
                System.out.println("\u001B[0m" + filosofo.getName() + " DEJÉ DE COMER  ---->");
                tenedor1.release();
                tenedor2.release();
                //Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            if (tomo1) {
                tenedor1.release();
            }
            if (tomo2) {
                tenedor2.release();
            }
        }
    }
}
