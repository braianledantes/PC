package Saludo_Jefe_Empleado;

public class Ejercicio1 {

    public static void main(String argv[]) {
        String[] nombresEmpleados = {"Nadina", "Juampi", "Agustin", "Valeria", "Silvia"};
        //Saludo hola = new Saludo(5);
        //Saludo hola = new SaludoLocks(5);
        Saludo hola = new SaludoMonitores(5);
        //Saludo hola = new SaludoExchange(nombresEmpleados.length);
        Personal[] elPersonal = new Personal[6];
        elPersonal[0] = new Personal(hola, "JEFE", true);

        for (int i = 1; i < 6; i++)
            elPersonal[i] = new Personal(hola, nombresEmpleados[i - 1], false);

        for (int i = 0; i < 6; i++)
            elPersonal[i].start();

        try {
            for (int i = 0; i < 6; i++)
                elPersonal[i].join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
