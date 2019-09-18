package TP2_Concurrente.ejercicio10;

import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        Semaphore sem_p1_p2 = new Semaphore(1);
        Semaphore sem_p2_p3 = new Semaphore(0);
        Semaphore sem_p3_p1 = new Semaphore(0);
        //Semaphore sem3 = new Semaphore(0);

        Thread p1 = new Thread(() -> {
            try {
                    sem_p1_p2.acquire();
                    System.out.println("soy " + Thread.currentThread().getName());
                    sem_p2_p3.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "P1");

        Thread p2 = new Thread(() -> {
            try {
                    sem_p2_p3.acquire();
                    System.out.println("soy " + Thread.currentThread().getName());
                    sem_p3_p1.release();
                    sem_p2_p3.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "P2");

        Thread p3 = new Thread(() -> {
            try {
                    sem_p3_p1.acquire();
                    System.out.println("soy " + Thread.currentThread().getName());
                    sem_p3_p1.release();
                    sem_p1_p2.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "P3");

        p1.start();
        p2.start();
        p3.start();
    }

}
