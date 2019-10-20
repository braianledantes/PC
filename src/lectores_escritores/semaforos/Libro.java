package lectores_escritores.semaforos;

import java.util.concurrent.Semaphore;

public class Libro {
    private Semaphore lectores, escritores, mutex;
    private int cantLectores, nLectores;
    private int nPaginas, nPaginasEscritas;


    public Libro(int cantLectores, int nPaginas) {
        this.lectores = new Semaphore(cantLectores);
        this.escritores = new Semaphore(1);
        this.mutex = new Semaphore(1);
        this.cantLectores = cantLectores;
        this.nPaginas = nPaginas;
        this.nPaginasEscritas = 0;
    }

    public boolean empezarEscribir(int idEscritor, int paginas) throws InterruptedException {
        escritores.acquire();
        lectores.acquire(cantLectores);
        // escribre las paginas que puede escribir
        int pagRestantes = nPaginas - nPaginasEscritas;
        paginas = Math.min(paginas, pagRestantes);
        nPaginasEscritas = nPaginasEscritas + paginas;
        return paginas > 0;
    }

    public void terminarEscribir(int idEscritor) {
        escritores.release();
        lectores.release(cantLectores);
    }

    public boolean empezarLeer(int idLector, int paginas) throws InterruptedException {
        mutex.acquire();
        nLectores++;
        if (nLectores == 1) {
            escritores.acquire();
        }
        mutex.release();
        lectores.acquire();
        mutex.acquire();
        paginas = Math.min(paginas, nPaginasEscritas);
        nPaginasEscritas = nPaginasEscritas - paginas;
        mutex.release();
        return paginas > 0;
    }

    public void terminarLeer(int idLector) throws InterruptedException {
        lectores.release();
        mutex.acquire();
        nLectores--;
        if (nLectores == 0) {
            escritores.release();
        }
        mutex.release();
    }

    public boolean puedoEscribir() throws InterruptedException {
        boolean re;
        mutex.acquire();
        re = nPaginasEscritas < nPaginas;
        mutex.release();
        return re;
    }

    public boolean puedoLeer() throws InterruptedException {
        boolean re;
        mutex.acquire();
        re = nPaginasEscritas > 0;
        mutex.release();
        return re;
    }
}
