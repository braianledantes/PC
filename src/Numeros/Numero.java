package Numeros;

public class Numero {
    private final int numero;
    private int cantidad;

    public Numero(int numero) {
        this.numero = numero;
        this.cantidad = 0;
    }

    public boolean esMismoNumero(int num) {
        return this.numero == num;
    }

    public synchronized void incrementar() {
        this.cantidad++;
    }

    public synchronized int getCantidad() {
        return cantidad;
    }

    public int getNumero() {
        return numero;
    }
}
