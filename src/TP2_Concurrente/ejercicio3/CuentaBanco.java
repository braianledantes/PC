package TP2_Concurrente.ejercicio3;

public class CuentaBanco {
    private int balance = 50;

    public CuentaBanco() {
    }

    public synchronized int getBalance() {
        return balance;
    }

    public synchronized boolean retiroBancario(int retiro) {
        if (balance > 0) {
            balance = balance - retiro;
            return true;
        } else return false;
    }
}
