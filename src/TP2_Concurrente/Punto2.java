package TP2_Concurrente;

public class Punto2 {

    public static void main(String[] args) {
        R recurso = new R();

        Thread A = new Thread(() -> {
            int t;
            while (true) {
                t = recurso.getTurno();
                if (t == 0) {
                    System.out.print("A");
                    recurso.pasoTurno();
                }
            }
        });
        Thread B = new Thread(() -> {
            int t;
            while (true) {
                t = recurso.getTurno();
                if (t == 1 || t == 2) {
                    System.out.print("B");
                    recurso.pasoTurno();
                }
            }
        });
        Thread C = new Thread(() -> {
            int t;
            while (true) {
                t = recurso.getTurno();
                if (t == 3 || t == 4 || t == 5) {
                    System.out.print("C");
                    recurso.pasoTurno();
                }
            }
        });

        A.start();
        B.start();
        C.start();

        try {
            A.join();
            B.join();
            C.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class R {
    private int turno;

    public R() {
        this.turno = 0;
    }

    public synchronized int getTurno() {
        return turno;
    }

    public synchronized void pasoTurno() {
        if (turno == 5) System.out.println();
        turno = (turno + 1) % 6;
    }
}
