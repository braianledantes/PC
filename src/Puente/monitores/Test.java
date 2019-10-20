package Puente.monitores;

public class Test {
    //todo no funciona correctamente
    public static void main(String[] args) {
        int cant = 7;
        Coche[] coches1 = new Coche[cant];
        Coche[] coches2 = new Coche[cant];
        int maxCochesPasando = 5;
        Puente puente = new Puente(maxCochesPasando);

        for (int i = 0; i < coches1.length; i++) {
            coches1[i] = new Coche(puente, Direccion.NORTE, i);
        }

        for (int i = 0; i < coches1.length; i++) {
            coches2[i] = new Coche(puente, Direccion.SUR, i + cant);
        }

        for (int i = 0; i < coches1.length; i++) {
            coches1[i].start();
        }

        for (int i = 0; i < coches1.length; i++) {
            coches2[i].start();
        }
        try {
            for (int i = 0; i < coches1.length; i++) {
                coches1[i].join();
            }

            for (int i = 0; i < coches1.length; i++) {
                coches2[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
