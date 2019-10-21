package Barbero_dormilon;

public class Main {
    public static void main(String[] args) {
        Barberia barberia = new Barberia(4);

        Thread barbero = new Thread(new Barbero(barberia));
        Thread[] clientes =  new Thread[7];

        barbero.start();
        for (int i = 0; i < clientes.length; i++) {
            clientes[i] = new Thread(new Cliente(barberia,i));
            clientes[i].start();
        }
    }
}
