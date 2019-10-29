package lectores_escritores.locks;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Libro {

    private ReentrantReadWriteLock rwl;

    public Libro() {
        rwl = new ReentrantReadWriteLock();
    }

    public void leer() {
        rwl.readLock().lock();
    }

    public void escribir() {
        rwl.writeLock().lock();
    }

    public void terminarLeer() {
        rwl.readLock().unlock();
    }

    public void terminarEscribir() {
        rwl.writeLock().unlock();
    }

}
