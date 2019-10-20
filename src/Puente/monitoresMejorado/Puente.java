package Puente.monitoresMejorado;

import colores.ColoresString;

import java.util.ArrayDeque;
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
        while (!direccion.equals(coche.getDireccion()) || cantActual == capacidad) {
            try {
                if (direccion == Direccion.NINGUNA) {
                    direccion = coche.getDireccion();
                    break;
                }
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        direccion = coche.getDireccion();
        cantActual++;
        fila.add(coche);
        imprimir(coche, true);
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
        if (fila.isEmpty()) {
            direccion = Direccion.NINGUNA;
        }
        imprimir(coche, false);
        notifyAll();
    }

    private void imprimir(Coche coche, boolean entrando) {
        String color, d;
        if (entrando) {
            d = "entrando";
            if (coche.getDireccion() == Direccion.NORTE) {
                color = ColoresString.ANSI_BLUE;
            } else {
                color = ColoresString.ANSI_RED;
            }
        } else {
            d = "saliendo";
            if (coche.getDireccion() == Direccion.NORTE) {
                color = ColoresString.ANSI_CYAN;
            } else {
                color = ColoresString.ANSI_PURPLE;
            }
        }
        System.out.println(color + "Coche " + coche.getIdCoche() + " " + d + " por " + coche.getDireccion());
    }
}
