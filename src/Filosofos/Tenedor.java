package Filosofos;

import java.util.concurrent.Semaphore;

public class Tenedor extends Semaphore {
    private String nombre;

    public Tenedor(int permits, String nombre) {
        super(permits);
        this.nombre = nombre;
    }

    public Tenedor(int permits, boolean fair, String nombre) {
        super(permits, fair);
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
