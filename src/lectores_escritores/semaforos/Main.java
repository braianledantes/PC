package lectores_escritores.semaforos;

/**
 * Un grupo de lectores/escritores quieren tener acceso a un libro.
 * Existen varios lectores, varios escritores y un libro con cantidad máxima
 * de páginas
 * Cuando un escritor quiere acceder a un libro éste debe estar
 * desocupado.
 * Utilizar:
 * -Semáforo,
 * -Lector,
 * -Escritor
 * Lector:
 * Puede haber uno o varios lectores leyendo.
 * Si hay un escritor, entonces el lector deberá esperar a que el
 * escritor acabe para poder leer
 * Escritor:
 * Si hay un escritor, entonces el escritor que quiere escribir debe
 * esperar a que no haya nadie leyendo, ni escribiendo
 */
public class Main {
    public static void main(String[] args) {
        int cantLectores = 4, cantEscritores = 3;
        Lector[] lectors = new Lector[cantLectores];
        Escritor[] escritors = new Escritor[cantEscritores];
        Libro libro = new Libro(cantLectores, 500);

        for (int i = 0; i < lectors.length; i++) {
            lectors[i] = new Lector(libro, i);
        }
        for (int i = 0; i < escritors.length; i++) {
            escritors[i] = new Escritor(libro, i);
        }

        for (int i = 0; i < lectors.length; i++) {
            lectors[i].start();
        }
        for (int i = 0; i < escritors.length; i++) {
            escritors[i].start();
        }
    }
}
