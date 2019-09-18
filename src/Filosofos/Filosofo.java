package Filosofos;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Filosofo extends Thread {
    private Semaphore tenedor1, tenedor2;
    private Random random;
    boolean tomo1, tomo2;

    public Filosofo(String name, Semaphore tenedor1, Semaphore tenedor2) {
        super(name);
        random = new Random(System.currentTimeMillis());
        this.tenedor1 = tenedor1;
        this.tenedor2 = tenedor2;
    }

    @Override
    public void run() {
        while (true) {
            intentarComer();
            //comer();
            pensar();
        }
    }

    public void intentarComer() {
        tomo1 = tenedor1.tryAcquire();
        tomo2 = tenedor2.tryAcquire();
        if (tomo1 && tomo2) {
            try {
                System.out.println(getName() + " estoy COMIENDO <----");
                Thread.sleep(1000 * (1 + random.nextInt(3)));
                System.out.println(getName() + " DEJÉ DE COMER  ---->");
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

    public void comer() {
        try {
            tenedor1.acquire();
            tenedor2.acquire();
            System.out.println(getName() + " estoy COMIENDO <----");
            Thread.sleep(1000 * (1 + random.nextInt(2)));
            //Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getName() + " DEJÉ DE COMER  ---->");
        tenedor1.release();
        tenedor2.release();
    }

    public void pensar() {
        try {
            System.out.println(getName() + " estoy pensando !!!!!");
            Thread.sleep(1000 * (1 + random.nextInt(3)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
