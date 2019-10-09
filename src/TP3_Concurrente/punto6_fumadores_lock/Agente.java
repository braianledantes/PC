package TP3_Concurrente.punto6_fumadores_lock;

import java.util.Random;

public class Agente implements Runnable {
    private SalaFumadores sala;
    private Random r;

    public Agente(SalaFumadores sala) {
        this.sala = sala;
        this.r = new Random();
    }

    @Override
    public void run() {
        while (true) {
            sala.colocar(r.nextInt(3) + 1);
        }
    }
}
