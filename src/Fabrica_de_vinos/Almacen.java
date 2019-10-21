package Fabrica_de_vinos;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Almacen {
    private Lock lock;
    private static final int jarras = 6;    //2
    private static final int unidadDeFermentacion = 7;   //1
    private static final int levadura = 15;//1
    private static final int jugo = 20;//2
    private static final int estacionDeMezcla = 2;

    private Condition enEstacion;
    private Condition cocinar;
    private Condition reponer;
    private Condition tomar;

    private int jarrasAct;
    private int unidadDeFermentacionAct;
    private int levaduraAct;
    private int jugoAct;
    private int estacionDeMezclaAct;

    public Almacen() {
        this.lock = new ReentrantLock();
        enEstacion = lock.newCondition();
        cocinar = lock.newCondition();
        reponer = lock.newCondition();
        enEstacion = lock.newCondition();
        jarrasAct = jarras;
        unidadDeFermentacionAct = unidadDeFermentacion;
        levaduraAct = levadura;
        jugoAct = jugo;
        estacionDeMezclaAct = estacionDeMezcla;
    }

    void iniciarMezcla() throws InterruptedException {
        lock.lock();
        while (!hayLugares()) {
            enEstacion.await();
        }
        estacionDeMezclaAct--;

        lock.unlock();
    }

    void mezclar() throws InterruptedException {
        lock.lock();
        while (jarrasAct < 2 && !hayIngredientes()) {
            if (!hayIngredientes()) {
                reponer.signal();
            }
            cocinar.await();
        }
        jarrasAct--;
        lock.unlock();
    }

    void dejarFermentar() {

    }

    void tomar() {

    }

    void reponerIngredientes() throws InterruptedException {
        while (hayIngredientes()) {
            reponer.await();
        }
    }

    private boolean hayLugares() {
        return estacionDeMezclaAct >= 1;
    }

    private boolean hayIngredientes() {
        return unidadDeFermentacionAct >= 1 && levaduraAct >= 1 && jugoAct >= 2;
    }
}
