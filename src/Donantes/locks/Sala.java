package Donantes.locks;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Sala {
    private int camillas, revistas;
    private Lock lock;
    private Condition revista, tv;
    private Random random;

    public Sala(int camillas, int revistas) {
        this.camillas = camillas;
        this.revistas = revistas;
        lock=new ReentrantLock(true);
        revista = lock.newCondition();
        tv=lock.newCondition();
        random = new Random();
    }

    public void entrar(int id) throws InterruptedException{
        lock.lock();
        while (camillas == 0){
            if (revistas > 0){
                System.out.println("Donante " + id + " tomo una revista");
                revistas--;
                revista.await();
                revistas++;
            } else {
                System.out.println("Donante " + id + " mirando TV");
                tv.await();
            }
        }
        camillas--;
        System.out.println("Donante " + id + " entro");
        lock.unlock();
    }

    public void donar(int id) throws InterruptedException {
        System.out.println("Donante " + id + " donando sangre");
        Thread.sleep(3000 + random.nextInt(7000));  // entre 3 y 10 segundos
    }

    public void salir(int id) throws InterruptedException {
        lock.lock();
        camillas++;
        revista.signal();
        tv.signal();
        System.out.println("Donante " + id + " chau");
        lock.unlock();
    }

}
