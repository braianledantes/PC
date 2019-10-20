package fumadores_monitores;

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
    protected int ingredienteQueFalta;
    protected boolean hayIngrediente = false;

    public synchronized void entraafumar(int id) throws InterruptedException {
        // solo sale del bucle cuando hayIngrediente == true y
        // el id del filosofo es el mismo que el ingrediente que falta
        while (!(hayIngrediente && id == ingredienteQueFalta)) {
            wait();
        }
    }

    public synchronized void terminarfumar() {
        hayIngrediente = false;
        notifyAll();
    }

    /**
     * El agente ingresa dos ingredientes menos el que se envia por parametro
     *
     * @param i ingrediente que falta
     */
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
