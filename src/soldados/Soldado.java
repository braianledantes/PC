package soldados;

import java.util.Random;

public class Soldado extends Thread {
    private int idSoldado;
    private Comedor comedor;

    public Soldado(int idSoldado, Comedor comedor) {
        this.idSoldado = idSoldado;
        this.comedor = comedor;
    }

    @Override
    public void run() {
        Random random = new Random();
        boolean quiereRefresco = random.nextBoolean();
        boolean quierePostre = random.nextBoolean();

        comedor.entrar();
        System.out.println("Soldado " + idSoldado + " entró al recinto");
        comedor.tomarBandeja();
        System.out.println("Soldado " + idSoldado + " tomo una bandeja");
        if (quiereRefresco) {
            comedor.tomarRefresco();
            System.out.println("Soldado " + idSoldado + " tomo refresco");
        } else {
            comedor.tomarAgua();
            System.out.println("Soldado " + idSoldado + " tomo agua");
        }
        if (quierePostre) {
            comedor.tomarPostre();
            System.out.println("Soldado " + idSoldado + " tomo postre");
        }
        comer(100000 + random.nextInt(100000));
        comedor.salir();
        System.out.println("Soldado " + idSoldado + " salio al recinto");
    }

    private void comer(int tiempo) {
        try {
            System.out.println("Soldado " + idSoldado + " comiendo");
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
