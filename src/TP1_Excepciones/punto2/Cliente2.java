package TP1_Excepciones.punto2;

public class Cliente2 implements Runnable {
    @Override
    public void run() {
        System.out.println("Soy " + Thread.currentThread().getName());
        Recurso.uso();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Cliente2(), "Juan Lopez");
        Thread t2 = new Thread(new Cliente2(), "Ines Gracia");
        t1.start();
        t2.start();
    }
}
