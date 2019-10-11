package TP4_Concurrente.Punto1_Saludo_Jefe_Empleado;

public class SaludoMonitoresMejorado extends SaludoMonitores implements Saludo {
    private final Object lockJefe;

    public SaludoMonitoresMejorado(int cantEmpleados) {
        super(cantEmpleados);
        this.lockJefe = new Object();
    }

    private synchronized void entrarSala() {
        cantEmpleadosActual++;
    }

    public void esperarJefe(String empleado) {
        entrarSala();
        synchronized (lockJefe) {
            lockJefe.notify();
        }
        synchronized (this) {
            while (!puedoSaludarAlJefe) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.out.println(e.toString());
                }
            }
            System.out.println(empleado + "> Buenos dias jefe!");
        }
    }

    public void saludoJefe() {
        synchronized (lockJefe) {
            while (cantEmpleadosActual < cantEmpleados) {
                System.out.println("(Esperando...)");
                try {
                    lockJefe.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("JEFE> Buenos dias!");
            puedoSaludarAlJefe = true;
            synchronized (this) {
                notifyAll();
            }
        }
    }
}
