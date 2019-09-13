package TP2_Concurrente.ejercicio3;

public class Main {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static void main(String[] args) {
        VerificarCuenta vc = new VerificarCuenta();
        Thread Luis = new Thread(vc, ANSI_GREEN);
        Thread Manuel = new Thread(vc, ANSI_RED);
        Luis.start();
        Manuel.start();
    }
}



