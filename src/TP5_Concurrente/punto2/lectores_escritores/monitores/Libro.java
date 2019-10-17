package TP5_Concurrente.punto2.lectores_escritores.monitores;

public class Libro {
    private int cantLectores;
    private int cantPaginas, paginasEscritas;
    boolean escribiedo;

    public Libro(int cantPaginas) {
        this.cantPaginas = cantPaginas;
        this.paginasEscritas = 0;
        this.cantLectores = 0;
        this.escribiedo = false;
    }

    public synchronized void empezarEscribir() throws InterruptedException {
        while (escribiedo || cantLectores > 0 || paginasEscritas == cantPaginas) {
            wait();
        }
        escribiedo = true;
    }

    public synchronized void escribirPaginas (int paginas) {
        int pagRestantes = cantPaginas - paginasEscritas;
        paginas = Math.min(paginas, pagRestantes);
        paginasEscritas = paginasEscritas + paginas;
    }

    public synchronized void terminarEscribir() {
        escribiedo = false;
        notifyAll();
    }

    public synchronized void empezarLeer() throws InterruptedException {
        while (escribiedo || paginasEscritas == 0) {
            wait();
        }
        cantLectores++;
    }

    public synchronized void leerPaginas(int paginas) {
        paginas = Math.min(paginas, paginasEscritas);
        paginasEscritas = paginasEscritas - paginas;
    }

    public synchronized void terminarLeer() {
        cantLectores--;
        notifyAll();
    }

    public synchronized int getPaginasEscritas() {
        return paginasEscritas;
    }

}
