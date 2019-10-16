package TP5_Concurrente.punto4_Puente.monitores;

import java.util.Random;

public class Coche extends Thread {
    /**
     * true: norte
     * false: sur
     */
    private int idCoche;
    private Puente puente;
    private Direccion direccion;

    public Coche(Puente puente, Direccion direccion, int idCoche) {
        this.direccion = direccion;
        this.idCoche = idCoche;
        this.puente = puente;
    }

    @Override
    public void run() {
        Random r = new Random(System.currentTimeMillis());
        int t = r.nextInt(1000);
        if (direccion.equals(Direccion.NORTE)) { // si pasa por el norte
            puente.entrarCoche(this);
            System.out.println("Coche " + idCoche + " entrando por NORTE");
            try {
                Thread.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Coche " + idCoche + " saliendo por NORTE");
            puente.salirCoche(this);
            direccion = Direccion.SUR;
        } else { // si pasa por el sur
            puente.entrarCoche(this);
            System.out.println("Coche " + idCoche + " entrando por SUR");
            try {
                Thread.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Coche " + idCoche + " saliendo por SUR");
            puente.salirCoche(this);
            direccion = Direccion.NORTE;
        }

    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public int getIdCoche() {
        return idCoche;
    }

    public void setIdCoche(int idCoche) {
        this.idCoche = idCoche;
    }

    @Override
    public String toString() {
        return "idCoche = " + idCoche + ", direccion = " + direccion;
    }
}
