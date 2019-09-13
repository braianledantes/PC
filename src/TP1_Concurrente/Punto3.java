package TP1_Concurrente;

public class Punto3 {

    public static void main(String[] args) {
        Thread hilo = new MiEjecucion();
        hilo.start();
        System.out.println("En el main");
    }
}

class MiEjecucion extends Thread {
    @Override
    public void run() {
        ir();
    }

    private void ir() {
        hacerMas();
    }

    private void hacerMas() {
        System.out.println("En la pila");
    }
}
