package Donantes.locks;

import java.util.concurrent.Semaphore;

public class SalaSemaforo implements Sala {
    Semaphore camillas = new Semaphore(4, true);
    Semaphore mutex = new Semaphore(1);
    int cantEsperando = 0;
    int cantMirandoTV = 0;
    Semaphore leyendoRevistas = new Semaphore(4, false);
    Semaphore mirandoTV = new Semaphore(6, false);

    @Override
    public boolean intentarEntrar() {
        return true;
    }

    @Override
    public boolean intentarEntrarASalsa() {
        return camillas.tryAcquire();
    }

    @Override
    public boolean intentarLeerRevista() {
        return leyendoRevistas.tryAcquire();
    }

    @Override
    public void mirarTV() {
        try {
            mirandoTV.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void donarSangre() {

    }

    @Override
    public void salir() {
        camillas.release();
        leyendoRevistas.release();

    }
}
