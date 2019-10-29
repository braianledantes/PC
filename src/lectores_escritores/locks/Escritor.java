package lectores_escritores.locks;

public class Escritor implements Runnable {

    private Libro libro;
    private int id;

    public Escritor(int id, Libro libro) {
        this.id = id;
        this.libro = libro;
    }

    public void run() {
        try {
            while (true) {
                libro.escribir();
                System.out.println("Escritor " + id + " esta escribiendo");
                Thread.sleep(1000);
                libro.terminarEscribir();
                System.out.println("Escritor " + id + " termino de escribir");
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
