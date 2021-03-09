package Numeros;

import java.util.Scanner;

public class TestNumeros {

    /*
     * Se tiene un arreglo de cantidad números generados aleatoriamente, menores a 100.
     * Se necesita averiguar cuántas repeticiones hay de un numero "nro" ingresado. Para agilizar el trabajo se
     * decide dividir la tarea entre varios hilos. Cada Hilo deberá ocuparse de una porción del arreglo. El hilo
     * principal debera mostrar el resultado final.
     * a) Resuelva el problema, considerando que el número de hilos es ingresado por el usuario. Por esta
     * razón puede ocurrir que el último hilo tenga que ocuparse de menos elementos que el resto.
     * b) Resuelva nuevamente, pero ahora asegúrese de que todos los hilos sean creados e iniciados antes
     * de que alguno empiece a procesar.
     * Nota: se sugiere hacer también un calculo secuencial para poder comparar los resultados
     */
    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);

        int[] arr = {1, 4, 3, 2, 3, 2, 1, 1, 3, 2};
        int buscado = 2;
        int cant = 0;
        for (int num : arr) {
            if (num == buscado) {
                cant++;
            }
        }

        System.out.println("cant de " + buscado + ": " + cant);
        System.out.println("cant elementos = " + arr.length);

        Numero numero = new Numero(buscado);

        Thread[] threads = new Contador[12];
        int cantPorHilo = arr.length / threads.length;
        System.out.println("cant por hilo: " + cantPorHilo);

        int posIni = 0, posFin = 0;
        for (int i = 0; i < threads.length; i++) {
            posIni = i * cantPorHilo;
            posFin = posIni + cantPorHilo - 1;
            if (i == threads.length - 1)
                posFin = arr.length - 1;

            threads[i] = new Contador(arr, numero, posIni, posFin);
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("cant de " + numero.getNumero() + ": " + numero.getCantidad());
    }

}
