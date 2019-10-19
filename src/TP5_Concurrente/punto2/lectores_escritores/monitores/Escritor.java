package TP5_Concurrente.punto2.lectores_escritores.monitores;

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
        Random random = new Random();
        int pag;
        while (true) {
            try {
                pag = 1 + random.nextInt(99);
                libro.empezarEscribir();
                System.out.println(ColoresString.ANSI_BLUE +
                        "Escritor " + idEscritor + " escribiendo " + pag + " paginas");
                libro.escribirPaginas(pag);
                System.out.println(ColoresString.ANSI_BLUE +
                        "Escritor " + idEscritor + " hay " + libro.getPaginasEscritas() + " pag");
                libro.terminarEscribir();
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
