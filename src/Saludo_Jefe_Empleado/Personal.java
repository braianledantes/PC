package Saludo_Jefe_Empleado;

public class Personal extends Thread {
    private String nombre;
    private Saludo saludo;
    private boolean esJefe;

    Personal(Saludo s, String n, boolean esJefe) {
        this.esJefe = esJefe;
        nombre = n;
        saludo = s;
    }

    public void run() {
        System.out.println("(" + nombre + " llega)");
        if (esJefe) {
            saludo.saludoJefe();
        } else {
            saludo.esperarJefe(nombre);
        }
    }
}
