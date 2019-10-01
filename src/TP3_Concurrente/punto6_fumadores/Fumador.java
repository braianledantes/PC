package TP3_Concurrente.punto6_fumadores;

public class Fumador implements Runnable{
    /**
     * El id del fumador tambien significa el ingrediente que le falta para armar el cigarrillo
     */
    private int id;
    private SalaFumadores sala;

    public Fumador(int id, SalaFumadores sala) {
        this.id = id;
        this.sala = sala;
    }

    @Override
    public void run() {
        System.out.println("llegó fumador "+ id);
        while (true) {
            try {
                sala.entraafumar(id);
                System.out.println("Fumador " + id + " está fumando.");
                Thread.sleep(1000);
                sala.terminarfumar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
