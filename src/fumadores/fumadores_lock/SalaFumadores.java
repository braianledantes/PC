package fumadores.fumadores_lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Braian Ledantes
 * @legajo FAI-1686
 */
public class SalaFumadores {
    private int mesa;
    private boolean alguienFuma;
    private Lock lock;
    private Condition[] puedoFumar;
    private Condition puedoColocar;

    public SalaFumadores() {
        lock = new ReentrantLock(true);
        puedoFumar = new Condition[3];
        puedoFumar[0] = lock.newCondition();
        puedoFumar[1] = lock.newCondition();
        puedoFumar[2] = lock.newCondition();
        puedoColocar = lock.newCondition();
        mesa = 0;
        alguienFuma = false;
    }

    public void entraafumar(int id) {
        lock.lock();
        try {
            while (mesa != id || alguienFuma) {
                puedoFumar[id - 1].await();
            }
            mesa = 0;
            alguienFuma = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void terminarfumar(int id) {
        lock.lock();
        alguienFuma = false;
        puedoColocar.signal();
        lock.unlock();
    }


    public void colocar(int ingrediente) {
        lock.lock();
        try {
            while (mesa != 0 || alguienFuma) {
                puedoColocar.await();
            }
            mesa = ingrediente;
            puedoFumar[mesa - 1].signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
