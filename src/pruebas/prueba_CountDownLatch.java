package pruebas;

import java.util.concurrent.CountDownLatch;

/**
 * Java Program to demonstrate how to use CountDownLatch, Its used when a thread
 * needs to wait for other threads before starting its work.
 *
 * @author Javin Paul
 */
public class prueba_CountDownLatch {

    public static void main(String args[]) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(4);
        Worker first = new Worker(1000, latch, "WORKER-1");
        Worker second = new Worker(2000, latch, "WORKER-2");
        Worker third = new Worker(3000, latch, "WORKER-3");
        Worker fourth = new Worker(4000, latch, "WORKER-4");
        Worker fiverth = new Worker(5000, latch, "WORKER-5");

        first.start();
        second.start();
        third.start();
        fourth.start();
        fiverth.start();

        // Main thread will wait until all thread finished
        latch.await();

        System.out.println(Thread.currentThread().getName() + " has started running again");

        latch.await();
        System.out.println(Thread.currentThread().getName() + " has finished");

    }
}

class Worker extends Thread {
    private int delay;
    private CountDownLatch latch;

    public Worker(int delay, CountDownLatch latch, String name) {
        super(name);
        this.delay = delay;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(delay);
            latch.countDown();
            System.out.println(Thread.currentThread().getName() + " has finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}