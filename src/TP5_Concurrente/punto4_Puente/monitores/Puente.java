package TP5_Concurrente.punto4_Puente.monitores;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Queue;

public class Puente {
    private int capacidad, cantActual;
    private Queue<Coche> fila;
    private Direccion direccion;

    public Puente(int capacidad) {
        this.capacidad = capacidad;
        this.cantActual = 0;
        this.fila = new ArrayDeque<>();
        direccion = Direccion.NINGUNA;
    }

    public synchronized void entrarCoche(Coche coche) {
        // si no viene nadie se manda
        if (direccion != Direccion.NINGUNA) {
            while (!direccion.equals(coche.getDireccion()) || cantActual == capacidad) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        direccion = coche.getDireccion();
        cantActual++;
        fila.add(coche);
        notifyAll();
    }

    public synchronized void salirCoche(Coche coche) {
        while (coche != fila.element()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        fila.poll();
        try {
            Coche nextCoche = fila.element();
           // System.out.println(nextCoche);
        } catch (NoSuchElementException e ) {
            direccion = Direccion.NINGUNA;
            cantActual =0;
        } finally {
            notifyAll();
        }
    }
}
