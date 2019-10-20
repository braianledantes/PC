package Puente.monitoresMejorado;

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
        while (true) {
            puente.entrarCoche(this);try {
                Thread.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            puente.salirCoche(this);
            if (direccion.equals(Direccion.NORTE)) {
                direccion = Direccion.SUR;
            } else {
               direccion = Direccion.NORTE;
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
}
