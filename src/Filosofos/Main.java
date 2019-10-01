package Filosofos;

public class Main {

    public static void main(String[] args) {
        int cant = 5;
        Filosofo[] filosofos = new Filosofo[cant];

        Mesa mesa = new Mesa(filosofos);
        mesa.setVisible(true);


       // Mesa mesa = new Mesa(cant);

        for (int i = 0; i < cant; i++) {
            filosofos[i] = new Filosofo("F" + i, i, mesa);
        }

       /* for (int i = 0; i < cant; i++) {
            filosofos[i].start();
        }*/
    }
}
