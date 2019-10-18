package TP5_Concurrente.punto3_soldados;

/**
 * En un cuartel hay un comedor para 100 soldados. El soldado cuando quiere comer entra en
 * el recinto y toma una bandeja con comida en uno de los 5 mostradores que existen para tal
 * efecto; la bandeja tiene un vaso de agua o un botellín de refresco, si escoge esto último
 * necesita uno de los 10 abridores. Si quiere postre se dirige a uno de los 3 mostradores que lo
 * despachan. Cuando finaliza la comida sale del recinto.
 *
 * Realizar un programa concurrente de forma que utilizando algún mecanismo de sincronización
 * coordine las tareas de los soldados.
 */
public class Main {
    public static void main(String[] args) {
        Soldado[] soldados = new Soldado[102];
        Comedor comedor = new Comedor();

        for (int i = 0; i < soldados.length; i++) {
            soldados[i] = new Soldado(i, comedor);
        }
        for (int i = 0; i < soldados.length; i++) {
            soldados[i].start();
        }
    }
}
