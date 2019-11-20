package pruebas;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Test_CyclicBarrier {

    static int waiting = 0;

    public static synchronized void incrementWaiting() {
        waiting++;
    }

    public static synchronized void resetWaiting() {
        waiting = 0;
    }

    static CyclicBarrier barrier1, barrier2;

    public static void main(String[] args) {
        barrier1 = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.err.println("BrokenBarrier with " + barrier1.getNumberWaiting() + " NumberWaiting");
                System.err.println("BrokenBarrier with " + waiting + " Waiting");
               // barrier2 = new CyclicBarrier(waiting);
            }
        });

        Thread h1 = new Hilo("h1", 1, barrier1);
        Thread h2 = new Hilo("h2", 2, barrier1);
        Thread h3 = new Hilo("h3", 4, barrier1);
        Thread h4 = new Hilo("h4", 5, barrier1);
        Thread h5 = new Hilo("h5", 6, barrier1);
        Thread h6 = new Hilo("h6", 8, barrier1);

        h1.start();
        h2.start();
        h3.start();
        h4.start();
        h5.start();
        h6.start();

        System.out.println("main thread finish");
    }


    private static class Hilo extends Thread {
        private int timeWorking;
        private CyclicBarrier barrier;

        public Hilo(String name, int timeWorking, CyclicBarrier barrier) {
            super(name);
            this.timeWorking = timeWorking;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            while (true) {

                try {
                    Thread.sleep(timeWorking * 1000);
                    incrementWaiting();
                    System.out.println(Thread.currentThread().getName() + " waiting1...");
                    barrier.await(2, TimeUnit.SECONDS);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                } catch (BrokenBarrierException bbe) {
                    System.err.println(Thread.currentThread().getName() + " BrokenBarrierException with " + barrier.getNumberWaiting() + " NumberWaiting");
                    System.err.println(Thread.currentThread().getName() + " BrokenBarrierException with " + waiting + " Waiting");
                    //bbe.printStackTrace();;
                } catch (TimeoutException e) {
                    //e.printStackTrace();
                    System.err.println(Thread.currentThread().getName() + " TimeoutException with " + barrier.getNumberWaiting() + " NumberWaiting");
                    System.err.println(Thread.currentThread().getName() + " TimeoutException with " + waiting + " Waiting");
                    barrier.reset();
                } finally {
                    if (barrier2 == null) {
                        barrier2 = new CyclicBarrier(waiting);
                    }
                    resetWaiting();
                }


                try {
                    System.out.println(Thread.currentThread().getName() + " waiting2...");
                    barrier2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } finally {
                    barrier2 = null;
                }
                System.out.println(Thread.currentThread().getName() + " finish");
            }
        }
    }

}

