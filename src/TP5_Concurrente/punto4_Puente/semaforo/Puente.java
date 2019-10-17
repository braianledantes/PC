package TP5_Concurrente.punto4_Puente.semaforo;

import java.util.concurrent.Semaphore;

public class Puente {
    private Semaphore semaphoreNorte, semaphoreSur, semaphoreCapacidad;
    private int capacidad, ultimo;

    public Puente(int capacidad) {
        this.capacidad = capacidad;
        this.ultimo = -1;
        this.semaphoreNorte = new Semaphore(capacidad);
        this.semaphoreSur = new Semaphore(capacidad);
        this.semaphoreCapacidad = new Semaphore(capacidad);
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
            if (entrada.availablePermits() == capacidad && salida.availablePermits() == capacidad) {
                salida.acquire(capacidad);
            }
            entrada.acquire();
            semaphoreCapacidad.acquire();
            ultimo = idcoche;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void salirCoche(Semaphore entrada, Semaphore salida, int idcoche) {
        semaphoreCapacidad.release();
        if (semaphoreCapacidad.availablePermits() == capacidad) {
            salida.release(capacidad);
            entrada.release(capacidad);
        }
    }
}
