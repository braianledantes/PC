package TP1_Excepciones.punto3;

public class MiEjecucion implements Runnable {

    public void run() {
        ir();
    }

    public void ir() {
        hacerMas();
    }

    public void hacerMas() {
        System.out.println("En la pila");
    }

    public static void main(String[] args) {
        Thread miHilo = new Thread(new MiEjecucion());
        miHilo.start();
        /*try {
            miHilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println("En el main");
    }

}
