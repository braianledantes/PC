package TP3_Concurrente.punto6_fumadores_lock;

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
    private Lock l;
    private Condition[] puedoFumar;
    private Condition puedoColocar;

    public SalaFumadores() {
        l = new ReentrantLock(true);
        puedoFumar = new Condition[3];
        puedoFumar[0] = l.newCondition();
        puedoFumar[1] = l.newCondition();
        puedoFumar[2] = l.newCondition();
        puedoColocar = l.newCondition();
        mesa = 0;
        alguienFuma = false;
    }

    public void entraafumar(int id) {
        try {
            l.lock();
            while (mesa != id || alguienFuma) {
                puedoFumar[id - 1].await();
            }
            mesa = 0;
            alguienFuma = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            l.unlock();
        }
    }

    public void terminarfumar(int id) {
        l.lock();
        alguienFuma = false;
        puedoColocar.signal();
        l.unlock();
    }


    public void colocar(int ingrediente) {
        l.lock();
        try {
            while (mesa != 0 || alguienFuma) {
                puedoColocar.await();
            }
            mesa = ingrediente;
            puedoFumar[mesa - 1].signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            l.unlock();
        }
    }
}
