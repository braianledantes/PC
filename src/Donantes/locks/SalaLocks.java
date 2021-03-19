package Donantes.locks;

import colores.ColoresString;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SalaLocks implements Sala {
    private final Lock lockCamillas = new ReentrantLock(true);
    private final Condition leyendoRevista = lockCamillas.newCondition();
    private final Lock lockTV = new ReentrantLock(false);
    private final Condition mirandoTV = lockTV.newCondition();

    private final Random random = new Random();

    private int revistarDisponibles;
    private final int cantCamillas;
    private int camillasOcupadas;
    private final int capacidad;
    private int cantActual;

    public SalaLocks(int cantCamillas, int revistarDisponibles, int capacidad) {
        this.cantCamillas = cantCamillas;
        this.camillasOcupadas = 0;
        this.revistarDisponibles = revistarDisponibles;
        this.capacidad = capacidad;
        this.cantActual = 0;
    }

    public boolean intentarEntrar() {
        lockCamillas.lock();
        boolean pudoEntrar = false;
        try {
            if (this.cantActual < this.capacidad) {
                pudoEntrar = true;
                this.cantActual++;
            }
        } finally {
            lockCamillas.unlock();
        }
        return pudoEntrar;
    }

    public boolean intentarEntrarASalsa() {
        lockCamillas.lock();
        boolean donar = false;
        try {
            if (camillasOcupadas < cantCamillas) {
                donar = true;
                camillasOcupadas++;
            }
        } finally {
            lockCamillas.unlock();
        }
        return donar;
    }

    public boolean intentarLeerRevista() {
        lockCamillas.lock();
        boolean pudoTomarRevista = false;
        try {
            if (revistarDisponibles > 0) {
                pudoTomarRevista = true;
                revistarDisponibles--;
                System.out.println(ColoresString.ANSI_GREEN + Thread.currentThread().getName() + " leyendo una revista...");
                leyendoRevista.await();
                //System.out.println(ColoresString.ANSI_GREEN + Thread.currentThread().getName() + " deja una revista...");
                revistarDisponibles++;
            }
        } catch (InterruptedException ignore) {
        } finally {
            lockCamillas.unlock();
        }

        if (pudoTomarRevista) {
            lockTV.lock();
            try {
                mirandoTV.signalAll();
            } finally {
                lockTV.unlock();
            }
        }
        return pudoTomarRevista;
    }

    public void mirarTV() {
        lockTV.lock();
        try {
            System.out.println(ColoresString.ANSI_BLUE + Thread.currentThread().getName() + " mirando TV...");
            mirandoTV.await();
            //System.out.println(ColoresString.ANSI_BLUE + Thread.currentThread().getName() + " dejo de mirar TV");
        } catch (InterruptedException ignore) {
        } finally {
            lockTV.unlock();
        }
    }

    public void donarSangre() {
        try {
            System.out.println(ColoresString.ANSI_RED + Thread.currentThread().getName() + " donando sangre");
            Thread.sleep(2000 + random.nextInt(4000));  // entre 3 y 10 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void salir() {
        lockCamillas.lock();
        try {
            leyendoRevista.signal();
            camillasOcupadas--;
            cantActual++;
            System.out.println(ColoresString.ANSI_BLACK + Thread.currentThread().getName() + " saliendo de la sala");
        } finally {
            lockCamillas.unlock();
        }
    }
}
