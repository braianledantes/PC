package TP3_Concurrente.punto2;

public class DualSynch {
    private Object syncObject = new Object();
    int dato = 5;

    public synchronized void f() {
        for (int i = 0; i < 5; i++) {
            dato = dato * 4;
            System.out.println("f()" + dato);
            //print("f()" + dato);
            Thread.yield();
        }
    }

    public synchronized void print(String cadena){
        System.out.println(cadena);
    }

    public void g() {
        synchronized (syncObject) {
            for (int i = 0; i < 5; i++) {
                dato = dato + 20;
                //System.out.println("g()" + dato);
                print("g()" + dato);
                Thread.yield();
            }
        }
    }
}
