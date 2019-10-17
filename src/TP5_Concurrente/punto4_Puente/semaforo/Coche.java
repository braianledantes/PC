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
            System.out.println("\u001B[31mCoche " + idCoche + " llego por NORTE");
            puente.entrarCochePorNorte(idCoche);
            System.out.println("\u001B[31mCoche " + idCoche + " entrando por NORTE");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("\u001B[0mCoche " + idCoche + " saliendo del NORTE");
            puente.salirCocheDelNorte(idCoche);
        } else { // si pasa por el sur
            System.out.println("\u001B[31mCoche " + idCoche + " llego por SUR");
            puente.entrarCochePorSur(idCoche);
            System.out.println("\u001B[31mCoche " + idCoche + " entrando por SUR");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("\u001B[0mCoche " + idCoche + " saliendo del SUR");
            puente.salirCocheDelSur(idCoche);
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
