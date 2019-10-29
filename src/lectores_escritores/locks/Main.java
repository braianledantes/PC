package lectores_escritores.locks;

public class Main {
    public static void main(String[] args) {

        Libro libro = new Libro();
        Thread[] lectores = new Thread[5];
        Thread[] escritores = new Thread[5];

        for (int i = 0; i < 5; i++) {
            escritores[i] = new Thread(new Escritor(i, libro));
            lectores[i] = new Thread(new Lector(i, libro));
            escritores[i].start();
            lectores[i].start();
        }

    }
}
