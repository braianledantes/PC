package Puente.semaforo;

import java.util.concurrent.Semaphore;

public class Puente {
    private final Semaphore semaphoreNorte;
    private final Semaphore semaphoreSur;
    private final Semaphore mutex;
    private final int capacidad;
    private int cruzando;

    public Puente(int capacidad) {
        this.capacidad = capacidad;
        this.semaphoreNorte = new Semaphore(capacidad, true);
        this.semaphoreSur = new Semaphore(capacidad, true);
        this.mutex = new Semaphore(1);
        this.cruzando = 0;
    }

    public void entrarCochePorNorte() {
        entrarCoche(semaphoreNorte, semaphoreSur);
    }

    public void salirCocheDelNorte() {
        salirCoche(semaphoreNorte, semaphoreSur);
    }

    public void entrarCochePorSur() {
        entrarCoche(semaphoreSur, semaphoreNorte);
    }

    public void salirCocheDelSur() {
        salirCoche(semaphoreSur, semaphoreNorte);
    }

    private void entrarCoche(Semaphore entrada, Semaphore salida) {
        try {
            mutex.acquire();
            if (cruzando == 0) {
                salida.acquire(capacidad);
            }
            mutex.release();
            entrada.acquire();
            mutex.acquire();
            cruzando++;
            mutex.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void salirCoche(Semaphore entrada, Semaphore salida) {
        try {
            entrada.release();
            mutex.acquire();
            cruzando--;
            if (cruzando == 0) {
                salida.release(capacidad);
            }
            mutex.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
