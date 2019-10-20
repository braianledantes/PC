package Puente.monitores;

import java.util.PriorityQueue;
import java.util.Queue;

public class Puente {
    private int capacidad, cantActual;
    private Queue<Coche> fila;
    private Direccion direccion;

    public Puente(int capacidad) {
        this.capacidad = capacidad;
        this.cantActual = 0;
        this.fila = new PriorityQueue<>();
        direccion = Direccion.NINGUNA;
    }

    public synchronized void entrarCochePorNorte(Coche coche) {
        // si no viene nadie se manda
        while (direccion == Direccion.NINGUNA || !direccion.equals(coche.getDireccion()) || cantActual == capacidad) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        direccion = coche.getDireccion();
        cantActual++;
        fila.add(coche);

    }

    public synchronized void salirCochePorNorte(Coche coche) {
        while (coche != fila.element()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Coche coche1 = fila.poll();
        if (coche1 == null) {
            direccion = Direccion.NINGUNA;
        }
    }

    public synchronized void entrarCochePorSur(Coche coche) {
    }

    public synchronized void salirCochePorSur(Coche coche) {
    }
}
