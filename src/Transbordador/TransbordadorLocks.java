package Transbordador;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TransbordadorLocks implements Transbordador {
    private final Lock lock = new ReentrantLock();
    private final Condition cSubir = lock.newCondition();
    private final Condition cBajar = lock.newCondition();
    private final Condition cViajar = lock.newCondition();

    private boolean subir = true;
    private boolean bajar = false;
    private int cantCoches = 0;

    @Override
    public void subir() {
        lock.lock();
        try {
            while (!subir || cantCoches >= 10) {
                cSubir.await();
            }
            this.cantCoches++;
            System.out.println(Thread.currentThread().getName() + " subio al transbordador");
            cViajar.signal();
        } catch (InterruptedException ignore) {
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void ir() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " esperando coches");
            while (cantCoches < 10) {
                cViajar.await();
            }

            System.out.println(Thread.currentThread().getName() + " yendo...");
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + " llego");

            subir = false;
            bajar = true;
            cBajar.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void bajar() {
        lock.lock();
        try {
            while (!bajar) {
                cBajar.await();
            }
            cantCoches--;
            System.out.println(Thread.currentThread().getName() + " bajo del transbordador");
            cViajar.signal();
        } catch (InterruptedException ignore) {
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void volver() {
        lock.lock();
        try {
            while (cantCoches > 0) {
                cViajar.await();
            }
            System.out.println(Thread.currentThread().getName() + " volviendo...");
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + " volvio");

            bajar = false;
            subir = true;
            cSubir.signalAll();
        } catch (InterruptedException ignore) {
        } finally {
            lock.unlock();
        }
    }
}
