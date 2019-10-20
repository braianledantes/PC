package Puente.semaforo;

import java.util.concurrent.Semaphore;

public class Puente {
    private Semaphore semaphoreNorte, semaphoreSur, mutex;
    private int capacidad, cruzando;

    public Puente(int capacidad) {
        this.capacidad = capacidad;
        this.semaphoreNorte = new Semaphore(capacidad, true);
        this.semaphoreSur = new Semaphore(capacidad, true);
        this.mutex = new Semaphore(1);
        this.cruzando = 0;
    }

    public void entrarCochePorNorte(int idcoche) {
        entrarCoche(semaphoreNorte, semaphoreSur, idcoche);
    }

    public void salirCocheDelNorte(int idcoche) {
        salirCoche(semaphoreNorte, semaphoreSur, idcoche);
    }

    public void entrarCochePorSur(int idcoche) {
        entrarCoche(semaphoreSur, semaphoreNorte, idcoche);
    }

    public void salirCocheDelSur(int idcoche) {
        salirCoche(semaphoreSur, semaphoreNorte, idcoche);
    }

    private void entrarCoche(Semaphore entrada, Semaphore salida, int idcoche) {
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

    private void salirCoche(Semaphore entrada, Semaphore salida, int idcoche) {
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
