package lectores_escritores.locks;

public class Lector implements Runnable {

    private Libro libro;
    private int id;

    public Lector(int id, Libro libro) {
        this.id = id;
        this.libro = libro;
    }

    public void run() {
        try {
            while (true) {
                libro.leer();
                System.out.println("Lector " + id + " esta leyendo");
                Thread.sleep(2000);
                libro.terminarLeer();
                System.out.println("Lector " + id + " termino de leer");
                Thread.sleep(4000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
