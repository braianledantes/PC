package Blancanieves_y_los_siete_enanitos;

import java.util.Random;

public class MesaMonitor implements Mesa {

    private Random random;
    int esperandoComer = 0;
    int sillasDisp;

    public MesaMonitor(int sillas) {
        this.random = new Random();
        this.sillasDisp = sillas;

    }

    @Override
    public synchronized void entrarAMesa() {
        while (sillasDisp == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        sillasDisp--;
        esperandoComer++;
        while (esperandoComer > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void comer() {
        try {
            Thread.sleep(1000 + random.nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void salirDeMesa() {
        sillasDisp++;
        notifyAll();
    }

    @Override
    public synchronized void servirComida() {
        if (esperandoComer > 0) {
            esperandoComer = 0;
            notifyAll();
        }
    }

}
