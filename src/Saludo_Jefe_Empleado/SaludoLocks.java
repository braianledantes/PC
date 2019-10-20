package Saludo_Jefe_Empleado;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaludoLocks implements Saludo {

    Lock lock;
    Condition saludoJefe, saludoEmpleados;
    int cantActual, cantEmpleados;

    public SaludoLocks(int cantEmpleados) {

        cantActual = 0;
        this.cantEmpleados = cantEmpleados;
        lock = new ReentrantLock();
        saludoEmpleados = lock.newCondition();
        saludoJefe = lock.newCondition();
    }

    public void esperarJefe(String nombreEmpleado) {
        lock.lock();
        cantActual++;
        try {
            if (cantActual == cantEmpleados) {
                saludoJefe.signal();
            }
            saludoEmpleados.await();
            System.out.println(nombreEmpleado + "> Buenos dias jefe!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void saludoJefe() {
        lock.lock();
        try {
            while (cantActual < cantEmpleados) {
                saludoJefe.await();
            }
            System.out.println("JEFE> Buenos dias!");
            saludoEmpleados.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
