package pruebas;

import java.util.concurrent.Semaphore;

public class Prueba_Parcial_Recuperatorio {

    public static void main(String[] args) {
        Semaphore s1 = new Semaphore(2);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    s1.acquire();
                    System.out.println("t1 tomo 1");
                    Thread.sleep(2000);
                    System.out.println("t1 libera 2");
                    s1.release(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    s1.acquire();
                    System.out.println("t2 tomo 1");
                    Thread.sleep(1000);
                    System.out.println("t2 libera 2");
                    s1.release(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("t3 quiere tomar 4");
                    s1.acquire(4);
                    System.out.println("t3 tomo 4");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t3.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.start();
        t2.start();

    }

}

