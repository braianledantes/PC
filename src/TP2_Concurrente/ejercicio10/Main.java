package TP2_Concurrente.ejercicio10;

import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        Semaphore sem_p1_p2 = new Semaphore(1);
        Semaphore sem_p2_p3 = new Semaphore(0);
        Semaphore sem_p3_p1 = new Semaphore(0);
        //Semaphore sem3 = new Semaphore(0);

        Thread p1 = new Thread(() -> {
            while (true) {
                if (sem_p1_p2.tryAcquire()) {
                    System.out.println("soy " + Thread.currentThread().getName());
                    sem_p2_p3.release();
                }
            }
        }, "P1");

        Thread p2 = new Thread(() -> {
            while (true) {
                if (sem_p2_p3.tryAcquire()) {
                    System.out.println("soy " + Thread.currentThread().getName());
                    sem_p3_p1.release();
                   // sem_p2_p3.release();
                }
            }
        }, "P2");

        Thread p3 = new Thread(() -> {
            while (true) {
                if (sem_p3_p1.tryAcquire()) {
                    System.out.println("soy " + Thread.currentThread().getName());
                    //sem_p3_p1.release();
                    sem_p1_p2.release();
                }
            }
        }, "P3");

        p1.start();
        p2.start();
        p3.start();
    }

}
