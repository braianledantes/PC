package lectores_escritores.semaforos;

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
        Random random = new Random(System.currentTimeMillis());
        boolean leyo;
        int pag;
        while (true) {
            try {
                if (libro.puedoLeer()) {
                    pag = 10 + random.nextInt(90);
                    leyo = libro.empezarLeer(idLector, pag);
                    System.out.println(ColoresString.ANSI_RED + "Lector " + idLector + " leyendo " + pag + " paginas");
                    if (leyo) Thread.sleep(2000);
                    System.out.println(ColoresString.ANSI_RED + "Lector " + idLector + " chau");
                    libro.terminarLeer(idLector);
                } else {
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public int getIdLector() {
        return idLector;
    }

    public void setIdLector(int idLector) {
        this.idLector = idLector;
    }
}
