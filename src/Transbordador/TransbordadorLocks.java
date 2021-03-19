package Transbordador;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TransbordadorLocks implements Transbordador {
    private final Lock lock = new ReentrantLock();
    private final Condition esperandoSubir = lock.newCondition();
    private final Condition esperandoBajar = lock.newCondition();
    private final Condition esperandoViajar = lock.newCondition();

    private boolean subir = true;
    private boolean bajar = false;
    private int cantCoches = 0;
    private final int canMaxima = 3;

    @Override
    public void subir() {
        lock.lock();
        try {
            while (!subir || cantCoches >= canMaxima) {
                esperandoSubir.await();
            }
            this.cantCoches++;
            System.out.println(Thread.currentThread().getName() + " subio al transbordador");
            esperandoViajar.signal();
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
            while (cantCoches < canMaxima) {
                esperandoViajar.await();
            }

            System.out.println(Thread.currentThread().getName() + " yendo...");
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + " llego");

            subir = false;
            bajar = true;
            esperandoBajar.signalAll();
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
                esperandoBajar.await();
            }
            cantCoches--;
            System.out.println(Thread.currentThread().getName() + " bajo del transbordador");
            esperandoViajar.signal();
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
                esperandoViajar.await();
            }
            System.out.println(Thread.currentThread().getName() + " volviendo...");
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + " volvio");

            bajar = false;
            subir = true;
            esperandoSubir.signalAll();
        } catch (InterruptedException ignore) {
        } finally {
            lock.unlock();
        }
    }
}
