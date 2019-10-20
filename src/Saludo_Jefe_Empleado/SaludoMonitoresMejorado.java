package Saludo_Jefe_Empleado;

public class SaludoMonitoresMejorado extends SaludoMonitores implements Saludo {
    private final Object lockJefe;
    private boolean puedoSaludarAEmpledos;

    public SaludoMonitoresMejorado(int cantEmpleados) {
        super(cantEmpleados);
        this.puedoSaludarAEmpledos = false;
        this.lockJefe = new Object();
    }

    public void esperarJefe(String empleado) {
        entrarSala();
        notificarAlJefe();
        saludarAlJefe(empleado);
    }

    private synchronized void entrarSala() {
        cantEmpleadosActual++;
        puedoSaludarAEmpledos = cantEmpleadosActual == cantEmpleados;
    }

    private void notificarAlJefe() {
        if (puedoSaludarAEmpledos) {
            synchronized (lockJefe) {
                lockJefe.notify();
            }
        }
    }

    private synchronized void saludarAlJefe(String empleado) {
        while (!puedoSaludarAlJefe) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.toString());
            }
        }
        System.out.println(empleado + "> Buenos dias jefe!");
    }

    public void saludoJefe() {
        synchronized (lockJefe) {
            while (!puedoSaludarAEmpledos) {
                System.out.println("(Esperando...)");
                try {
                    lockJefe.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("JEFE> Buenos dias!");
            puedoSaludarAlJefe = true;
            notificarALosEmpleados();
        }
    }

    private synchronized void notificarALosEmpleados() {
        this.notifyAll();
    }
}
