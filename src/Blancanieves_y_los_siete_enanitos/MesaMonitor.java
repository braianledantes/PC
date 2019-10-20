package Blancanieves_y_los_siete_enanitos;

import java.util.Random;

public class MesaMonitor implements Mesa {
    private int lugares;
    private int platosServidos;
    private int lugaresOcupados;
    private int esperando;
    private Random random;

    public MesaMonitor(int lugares) {
        this.lugares = lugares;
        this.platosServidos = 0;
        this.lugaresOcupados = 0;
        this.esperando = 0;
        this.random = new Random();
    }

    @Override
    public synchronized void entrarAMesa() {
       esperando++;
       while (platosServidos == 0 || lugaresOcupados > lugares) {
           try {
               wait();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
       esperando--;
       lugaresOcupados++;
    }

    @Override
    public void comer() {
        try {
            Thread.sleep(1000 + random.nextInt(3000));
            platosServidos--;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void salirDeMesa() {
        lugaresOcupados--;
    }

    @Override
    public synchronized void servirComida() {
        platosServidos = Math.min(esperando, 4);
        notifyAll();
    }
}
