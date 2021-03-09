package fumadores;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MesaLocks implements Mesa {
    private final Lock lock = new ReentrantLock();
    private final Condition agente = lock.newCondition();
    private final Condition fumadores[];
    private int ingredienteFaltante = 0;

    public MesaLocks(int cantFumadores) {
        this.fumadores = new Condition[cantFumadores];
        for (int i = 0; i < fumadores.length; i++) {
            fumadores[i] = lock.newCondition();
        }
    }

    @Override
    public void agregarIngredientes() {
        lock.lock();
        try {
            while (ingredienteFaltante != 0) {
                agente.await();
            }
            Thread.sleep((int) (Math.floor(Math.random() * (2000 - 1000 + 1)) + 1000));
            ingredienteFaltante = (int) (Math.floor(Math.random() * fumadores.length) + 1);
            System.out.println(Thread.currentThread().getName() + " agrego un ingrediente " + ingredienteFaltante);
            fumadores[ingredienteFaltante - 1].signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void armarCigarrillo(int ingrediente) {
        lock.lock();
        try {
            while (ingredienteFaltante != ingrediente) {
                fumadores[ingrediente - 1].await();
            }
            Thread.sleep((int) (Math.floor(Math.random() * (2000 - 1000 + 1)) + 1000));
            ingredienteFaltante = 0;
            System.out.println(Thread.currentThread().getName() + " armo un cigarro con " + ingrediente);
            agente.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
