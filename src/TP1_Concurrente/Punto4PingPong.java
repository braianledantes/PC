package TP1_Concurrente;

public class Punto4PingPong {

    public static void main(String[] args) {
        Thread p1 = new PingPong("PING", 33);
        Thread p2 = new PingPong("PONG", 10);
        Thread p3 = new PingPong("PANG", 15);
        Thread p4 = new PingPong("PUNG", 21);

        p1.start();
        p2.start();

        for (int i = 0; i < 2000; i++) {
            System.out.println("hola");
        }

        /*
        try {
            Thread.sleep(5* 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}

class PingPong extends Thread {
    private String cadena;
    private int delay;

    public PingPong(String cartel, int cantMs){
        this.cadena = cartel;
        this.delay = cantMs;
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    @Override
    public void run() {
        for (int i = 1; i < delay * 10; i++) {
            System.out.println(cadena + " ");

            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
