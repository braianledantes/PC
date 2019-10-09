package TP3_Concurrente.punto5;

// a)
public class SynchronizedCounter {
    private int c = 0;

    public synchronized void increment() {
        c++;
    }

    public void decrement() {
        c--;
    }

    public synchronized int value() {
        return c;
    }
}
