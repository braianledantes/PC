package lectores_escritores.semaforos;

import colores.ColoresString;

import java.util.Random;

public class Escritor extends Thread {
    private Libro libro;
    private int idEscritor;

    public Escritor(Libro libro, int idEscritor) {
        this.libro = libro;
        this.idEscritor = idEscritor;
    }

    @Override
    public void run() {
        Random random = new Random(System.currentTimeMillis());
        boolean escribio;
        int pag;
        while (true) {
            try {
                if (libro.puedoEscribir()) {
                    pag = 1 + random.nextInt(99);
                    escribio = libro.empezarEscribir(idEscritor, pag);
                    System.out.println(ColoresString.ANSI_BLUE + "Escritor " + idEscritor + " escribiendo " + pag + " paginas");
                    if (escribio) {
                        Thread.sleep(3000);
                    }
                    System.out.println(ColoresString.ANSI_BLUE + "Escritor " + idEscritor + " chau");
                    libro.terminarEscribir(idEscritor);
                    //Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
