package TP5_Concurrente.punto4_Puente.semaforo;

import java.util.concurrent.Semaphore;

public class Puente {
    private Semaphore semaphoreNorte, semaphoreSur;
    private int cant, ultimo;

    public Puente(int cant) {
        this.cant = cant;
        this.ultimo = -1;
        this.semaphoreNorte = new Semaphore(cant);
        this.semaphoreSur = new Semaphore(cant);
    }

    public void entrarCochePorNorte(int idcoche) {
        try {
            if (semaphoreSur.availablePermits() == cant && semaphoreNorte.availablePermits() == cant) {
                semaphoreSur.acquire(cant);
            }
            semaphoreNorte.acquire();
          //  if (semaphoreSur.availablePermits() == 0) {
                ultimo = idcoche;
           // }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void salirCochePorNorte(int idcoche) {
        if (ultimo == idcoche) {
            semaphoreSur.release(cant);
        }
    }

    public void entrarCochePorSur(int idcoche) {
        try {
            if (semaphoreSur.availablePermits() == cant && semaphoreNorte.availablePermits() == cant) {
                semaphoreNorte.acquire(cant);
            }
            semaphoreSur.acquire();
           // if (semaphoreSur.availablePermits() == 0) {
                ultimo = idcoche;
           // }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void salirCochePorSur(int idcoche) {
        if (ultimo == idcoche) {
            semaphoreNorte.release(cant);
        }
    }
}
