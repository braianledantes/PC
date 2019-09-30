package TP3_Concurrente.punto6_fumadores;

public class Fumador implements Runnable{

    //private Surtido surtido;
    private int id;
    private SalaFumadores sala;

    public Fumador(int id, SalaFumadores sala) {
        this.id = id;
        this.sala = sala;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sala.entraafumar(id);
                System.out.println("Fuamdor " + id + " está fumando.");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
