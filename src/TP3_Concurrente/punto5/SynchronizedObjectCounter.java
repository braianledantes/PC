package TP3_Concurrente.punto5;

/**
 * - Verifique el funcionamiento del codigo del inciso a)
 * - Verifique el funcionamiento del codigo del inciso b)
 * - En caso de ser necesario realice las correcciones que crea convenientes.
 * - Compare entre usar, en este caso, métodos sincrozados con objetos sincronizados. (Ventajas y Desventajas)
 */

// b)
public class SynchronizedObjectCounter {
    private int c = 0;

    public void increment() {
        //synchronized (c) { estaba así en el TP
        synchronized (this) {
            c++;
        }
    }

    public void decrement() {
        synchronized (this) {
            c--;
        }
    }

    public int value() {
        return c;
    }
}

