package Puente.semaforo;

import colores.ColoresString;

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
        int t;
        String idCocheString = "" + idCoche;
        if (idCoche < 10)  idCocheString = "0" + idCocheString;
        while (true) {
            t = r.nextInt(1000);
            if (lado) { // si pasa por el norte
                puente.entrarCochePorNorte(idCoche);
                System.out.println(ColoresString.ANSI_BLUE + "Coche " + idCocheString + " entro por NORTE       <---");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(ColoresString.ANSI_BLUE + "Coche " + idCocheString + " salio del NORTE       --->");
                puente.salirCocheDelNorte(idCoche);
            } else { // si pasa por el sur
                puente.entrarCochePorSur(idCoche);
                System.out.println(ColoresString.ANSI_GREEN + "Coche " + idCocheString + " entro por SUR         <---");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(ColoresString.ANSI_GREEN + "Coche " + idCocheString + " salio del SUR         --->");
                puente.salirCocheDelSur(idCoche);
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lado = !lado;
        }
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
