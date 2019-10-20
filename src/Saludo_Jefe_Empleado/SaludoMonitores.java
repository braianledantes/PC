package Saludo_Jefe_Empleado;

public class SaludoMonitores implements Saludo{
    protected int cantEmpleadosActual;
    protected int cantEmpleados;
    protected boolean puedoSaludarAlJefe;

    public SaludoMonitores(int cantEmpleados) {
        this.cantEmpleados = cantEmpleados;
        this.puedoSaludarAlJefe = false;
        this.cantEmpleadosActual = 0;
    }

    public synchronized void esperarJefe(String empleado) {
        cantEmpleadosActual++;
        notifyAll();
        while (!puedoSaludarAlJefe) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.toString());
            }
        }
        System.out.println(empleado + "> Buenos dias jefe!");
    }

    public synchronized void saludoJefe() {
        while (cantEmpleadosActual < cantEmpleados) {
            System.out.println("(Esperando...)");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("JEFE> Buenos dias!");
        puedoSaludarAlJefe = true;
        // notify();  El error estaba acá, solo notificaba a un solo empleado
        notifyAll();
    }
}
