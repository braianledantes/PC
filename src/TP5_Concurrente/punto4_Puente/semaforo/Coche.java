package TP5_Concurrente.punto4_Puente.semaforo;

import java.util.Random;

public class Coche extends Thread {
    /**
     * true: norte
     * false: sur
     */
    private boolean lado;
    private int idCoche;
    private Puente puente;

    public Coche(Puente puente, boolean lado, int idCoche) {
        this.lado = lado;
        this.idCoche = idCoche;
        this.puente = puente;
    }

    @Override
    public void run() {
        Random r = new Random(System.currentTimeMillis());
        int t = r.nextInt(1000);
        if (lado) { // si pasa por el norte
            puente.entrarCochePorNorte(idCoche);
            System.out.println("Coche " + idCoche + " entrando por NORTE");
            try {
                Thread.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Coche " + idCoche + " saliendo por NORTE");
            puente.salirCochePorNorte(idCoche);
        } else { // si pasa por el sur
            puente.entrarCochePorSur(idCoche);
            System.out.println("Coche " + idCoche + " entrando por SUR");
            try {
                Thread.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Coche " + idCoche + " saliendo por SUR");
            puente.salirCochePorSur(idCoche);
        }
        lado = !lado;
    }

    public boolean getLado() {
        return lado;
    }

    public void setLado(boolean lado) {
        this.lado = lado;
    }

    public int getIdCoche() {
        return idCoche;
    }

    public void setIdCoche(int idCoche) {
        this.idCoche = idCoche;
    }
}
