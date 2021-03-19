package Donantes.locks;

import colores.ColoresString;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SalaParcial {
    private int cantCamillas, cantRevistas;
    private final Lock lock;
    private final Condition leyendoRevistas, viendoTV;
    private final Random random;

    public SalaParcial(int cantCamillas, int cantRevistas) {
        this.cantCamillas = cantCamillas;
        this.cantRevistas = cantRevistas;
        lock = new ReentrantLock(true);
        leyendoRevistas = lock.newCondition();
        viendoTV = lock.newCondition();
        random = new Random();
    }

    public void esperarASerAtendido() throws InterruptedException {
        lock.lock();
        String threadName = Thread.currentThread().getName();
        //System.out.println(ColoresString.ANSI_RESET + threadName + " entro a la sala");
        while (cantCamillas == 0) {
            if (cantRevistas > 0) {
                System.out.println(ColoresString.ANSI_GREEN + threadName + " leyendo una revista...");
                cantRevistas--;
                leyendoRevistas.await();
                cantRevistas++;
            } else {
                System.out.println(ColoresString.ANSI_BLUE + threadName + " mirando TV...");
                viendoTV.await();
                System.out.println(ColoresString.ANSI_BLUE + threadName + " dejo de mirar TV");
            }
        }
        cantCamillas--;
        lock.unlock();
    }

    public void donar() throws InterruptedException {
        System.out.println(ColoresString.ANSI_RED + Thread.currentThread().getName() + " donando sangre");
        Thread.sleep(3000 + random.nextInt(7000));  // entre 3 y 10 segundos
    }

    public void salir() throws InterruptedException {
        lock.lock();
        cantCamillas++;
        leyendoRevistas.signal();
        viendoTV.signal();
        System.out.println(ColoresString.ANSI_BLACK + Thread.currentThread().getName() + " salio de la sala");
        lock.unlock();
    }

}
