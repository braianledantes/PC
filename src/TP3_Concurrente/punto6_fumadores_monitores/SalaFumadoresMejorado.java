package TP3_Concurrente.punto6_fumadores_monitores;

public class SalaFumadoresMejorado extends SalaFumadores {
    protected Object cerrojoAgente = new Object();

    public synchronized void entraafumar(int id) throws InterruptedException {
        while (!(hayIngrediente && id == ingredienteQueFalta)) {
            wait();
        }
    }

    public void terminarfumar() {
        synchronized (cerrojoAgente) {
            hayIngrediente = false;
            cerrojoAgente.notifyAll();
        }
    }

    public synchronized void colocar(int i) {
        while (hayIngrediente) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ingredienteQueFalta = i;
        hayIngrediente = true;
        notifyAll();
    }
}
