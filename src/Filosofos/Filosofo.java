package Filosofos;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Filosofo extends Thread {
    private Mesa mesa;
    private Random random;
    private int pos;

    public Filosofo(String name, int pos, Mesa mesa) {
        super(name);
        this.pos = pos;
        this.mesa = mesa;
        this.random = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() {
        while (true) {
            //intentarComer();
            //comer();
            //mesa.comer(this);
            mesa.comerVisual(this);
            pensar();
        }
    }

    public void pensar() {
        try {
            //System.out.println("\u001B[0m" + getName() + " estoy pensando !!!!!");
            Thread.sleep(1000 * (2 + random.nextInt(4)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
}
