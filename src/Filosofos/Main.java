package Filosofos;

import java.util.concurrent.Semaphore;

public class Main {

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
        int cant = 5;
        String[] color = {ANSI_RED, ANSI_GREEN, ANSI_YELLOW, ANSI_BLUE, ANSI_PURPLE, ANSI_CYAN, ANSI_BLACK, ANSI_WHITE,ANSI_RED, ANSI_GREEN, ANSI_YELLOW, ANSI_BLUE, ANSI_PURPLE, ANSI_CYAN, ANSI_BLACK, ANSI_WHITE};
        Filosofo[] filosofos = new Filosofo[cant];
        Semaphore[] tenedores = new Semaphore[cant];

        for (int i = 0; i < cant; i++) {
            tenedores[i] = new Semaphore(1, true);
        }

        for (int i = 0; i < cant; i++) {
            filosofos[i] = new Filosofo(color[i] + i, tenedores[i], tenedores[(i + 1) % cant]);
        }

        for (int i = 0; i < cant; i++) {
            filosofos[i].start();
        }

        for (int i = 0; i < cant; i++) {
            try {
                filosofos[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(ANSI_RESET + "programa finalizado");
    }
}
