package Puente.semaforo;

public class Test {
    public static void main(String[] args) {
        int cantNorte = 5, cantSur = 3;
        Coche[] coches1 = new Coche[cantNorte];
        Coche[] coches2 = new Coche[cantSur];
        int maxCochesPasando = 5;
        Puente puente = new Puente(maxCochesPasando);

        for (int i = 0; i < coches1.length; i++) {
            coches1[i] = new Coche(puente, true, i);
        }

        for (int i = 0; i < coches2.length; i++) {
            coches2[i] = new Coche(puente, false, i + cantNorte);
        }

        for (int i = 0; i < coches1.length; i++) {
            coches1[i].start();
        }

        for (int i = 0; i < coches2.length; i++) {
            coches2[i].start();
        }
        try {
            for (int i = 0; i < coches1.length; i++) {
                coches1[i].join();
            }

            for (int i = 0; i < coches2.length; i++) {
                coches2[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
