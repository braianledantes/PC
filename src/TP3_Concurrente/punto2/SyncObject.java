package TP3_Concurrente.punto2;

public class SyncObject {

    public static void main(String[] args) {
        final DualSynch ds = new DualSynch();
        Thread hilo = new Thread() {
            public void run() {
                ds.f();
            }
        };
        hilo.start();
        ds.g();
    }
}
