package TP2_Concurrente;

/**
 * @author Braian Ledantes
 * @legajo FAI-1686
 * @date 06/09/2019
 */

import java.util.concurrent.Semaphore;

/**
 * Realice un programa en donde se muestre el comportamiento de dos hilos que acceden a
 * un recurso compartido VALOR que se inicializa con 3, de la siguiente manera:
 * a. La Tarea A (hilo 1) realiza TOTAL++.
 * b. La tarea B (hilo 2) realiza TOTAL*=2.
 * Debe tener en cuenta que las operaciones son: tomar el valor TOTAL, operarlo y
 * volverlo a guardar.
 * a) Pruebe la ejecución varias veces e indique el valor resultante en cada una.
 * b) ¿Qué puede concluir?
 * c) Ahora modifique el programa utilizando alguna herramienta para sincronizar la
 * ejecución.
 */
public class Punto4 {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void main(String[] args) {
        Recurso r = new Recurso(1,3);
        Thread tareaA, tareaB;

        System.out.println(ANSI_RED);
        for (int i = 0; i < 10; i++) {
            r.setValor(3);

            tareaA = new Thread(() -> r.setValor(r.getValor() + 1), "Suma");
            tareaB = new Thread(() -> r.setValor(r.getValor() * 2), "Mult");

            tareaA.start();
            tareaB.start();

            try {
                tareaA.join();
                tareaB.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("El valor del recurso es: " + r.getValor());
            System.out.println("--------------------------");
        }

        // a) RTA: imprime 8, 4 ó 7
        // b) RTA: que al utilizar los metodos geters y seters no se mantiene la consistencia de los datos aunque esten sincronizados.
        //         Se debe utilizar otra tecnica para realizar la operacion.
        // c) RTA: ok, debe imprimir 8 = ((3 + 1) * 2) ó 7 = ((3 * 2) + 1)

        System.out.println(ANSI_GREEN);

        for (int i = 0; i < 10; i++) {
            r.setValor(3);

            tareaA = new Thread(() -> {
                try {
                    r.acquire();
                    r.setValor(r.getValor() + 1);
                    r.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "Suma");

            tareaB = new Thread(() -> {
                try {
                    r.acquire();
                    r.setValor(r.getValor() * 2);
                    r.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "Mult");

            tareaA.start();
            tareaB.start();

            try {
                tareaA.join();
                tareaB.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("El valor del recurso es: " + r.getValor());
            System.out.println("--------------------------");
        }
    }
}

class Recurso extends Semaphore {
    private int valor;

    public Recurso(int permits, int valor) {
        super(permits);
        this.valor = valor;
    }

    public synchronized int getValor() {
        System.out.println(Thread.currentThread().getName() + ": getTotal() -> " + valor);
        return valor;
    }

    public synchronized void setValor(int valor) {
        this.valor = valor;
        System.out.println(Thread.currentThread().getName() + ": setTotal(" + valor + ")");
    }
}
