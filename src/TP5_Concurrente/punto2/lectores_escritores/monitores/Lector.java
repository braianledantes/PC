package TP5_Concurrente.punto2.lectores_escritores.monitores;

import colores.ColoresString;

import java.util.Random;

public class Lector extends Thread {
    private Libro libro;
    private int idLector;

    public Lector(Libro libro, int idLector) {
        this.libro = libro;
        this.idLector = idLector;
    }

    @Override
    public void run() {
        Random random = new Random();
        int pag;
        while (true) {
            try {
                pag = 10 + random.nextInt(40);
                libro.empezarLeer();
                System.out.println(ColoresString.ANSI_RED +
                        "Lector " + idLector + " leyendo " + pag + " paginas");
                Thread.sleep(20 * pag);
                libro.leerPaginas(pag);
                System.out.println(ColoresString.ANSI_RED +
                        "Lector " + idLector + " hay " + libro.getPaginasEscritas() + " pag");
                libro.terminarLeer();
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
