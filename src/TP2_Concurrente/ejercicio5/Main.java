package TP2_Concurrente.ejercicio5;

public class Main {
    public static void main(String[] args) {
        EstacionDeServicio estacionDeServicio = new EstacionDeServicio(1);
        Vehiculo vehiculos[] = new Vehiculo[2];
        for (int i = 0; i < vehiculos.length; i++) {
            vehiculos[i] = new Auto("FNI-" + (100 + i), 100, 3, 100, estacionDeServicio);
        }

        for (int i = 0; i < vehiculos.length; i++) {
            vehiculos[i].start();
        }

        for (int i = 0; i < vehiculos.length; i++) {
            try {
                vehiculos[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
