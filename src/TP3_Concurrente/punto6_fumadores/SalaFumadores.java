package TP3_Concurrente.punto6_fumadores;

/**
 * @author Braian Ledantes
 * @legajo FAI-1686
 */
public class SalaFumadores {
    /**
     * Como cada fumador solo tiene un ingrediente para armar el cigarro,
     * consideré que el id del fumador es el ingrediente que tiene y
     * el agente solo comunica el ingrediente que falta en la mesa.
     */
    private int ingredienteQueFalta;
    private boolean fumando;

    public synchronized void entraafumar(int id) {
        while (id != ingredienteQueFalta) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        fumando = true;
    }

    /**
     * El agente ingresa dos ingredientes menos el que se envia por parametro
     *
     * @param i ingrediente que falta
     */
    public synchronized void colocar(int i) {
        if (!fumando) {
            ingredienteQueFalta = i;
            notifyAll();
        }
    }

    public synchronized void terminarfumar() {
        fumando = false;
        notifyAll(); // notifica a todos los hilos que continuen con su ejecucion
    }
}
