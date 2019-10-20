package soldados;

import java.util.concurrent.Semaphore;

/**
 * En un cuartel hay un comedor para 100 soldados. El soldado cuando quiere comer entra en
 * el recinto y toma una bandeja con comida en uno de los 5 mostradores que existen para tal
 * efecto; la bandeja tiene un vaso de agua o un botellín de refresco, si escoge esto último
 * necesita uno de los 10 abridores. Si quiere postre se dirige a uno de los 3 mostradores que lo
 * despachan. Cuando finaliza la comida sale del recinto.
 * <p>
 * Realizar un programa concurrente de forma que utilizando algún mecanismo de sincronización
 * coordine las tareas de los soldados.
 */
public class Comedor {

    private Semaphore entradas;
    private Semaphore mostradoresBandejas;
    private Semaphore abridores;
    private Semaphore mostradoresPostres;

    public Comedor() {
        entradas = new Semaphore(100);
        mostradoresBandejas = new Semaphore(5);
        abridores = new Semaphore(10);
        mostradoresPostres = new Semaphore(3);
    }

    public void entrar() {
        try {
            entradas.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void tomarBandeja() {
        try {
            mostradoresBandejas.acquire();
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mostradoresBandejas.release();
        }
    }

    public void tomarRefresco() {
        try {
            abridores.acquire();
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            abridores.release();
        }
    }

    public void tomarAgua() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void tomarPostre() {
        try {
            mostradoresPostres.acquire();
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mostradoresPostres.release();
        }
    }

    public void salir() {
        entradas.release();
    }
}
