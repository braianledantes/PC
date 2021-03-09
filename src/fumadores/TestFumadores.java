package fumadores;

public class TestFumadores {

    /*
     * El problema de los fumadores de cigarrillos es otro problema clásico de sincronización.
     * Básicamente, es un problema de coordinación entre un agente y tres fumadores en el que
     * intervienen tres tipos de ingredientes: i) papel, ii) tabaco y iii) fósforos.
     *
     * Por una parte, el agente dispone de una cantidad ilimitada respecto a los tres ingredientes.
     * Por otra parte, cada fumador dispone de un único elemento, también en cantidad ilimitada,
     * es decir, un fumador dispone de papel, otro de tabaco y otro de fósforos.
     * Cada cierto tiempo, el agente coloca sobre una mesa, de manera aleatoria, dos de los tres
     * ingredientes necesarios para armarse un cigarrillo (ver figura 3.11). El fumador que tiene el
     * ingrediente restante toma los otros dos de la mesa, se arma el cigarrillo y se lo notifica al
     * agente. Este ciclo se repite indefinidamente.
     *
     * La problemática principal reside en coordinar adecuadamente al agente y a los fumadores, considerando las
     * siguientes acciones:
     * 1. El agente pone dos elementos sobre la mesa.
     * 2. El agente notifica al fumador que tiene el tercer elemento que ya puede armarse un cigarrillo (se simula el
     * acceso del fumador a la mesa).
     * 3. El agente espera a que el fumador le notifique que ya ha terminado de armarse el cigarrillo.
     * 4. El fumador notifica que ya ha terminado de armarse el cigarrillo.
     * 5. El agente vuelve a poner dos nuevos elementos sobre la mesa, repitiéndose así el ciclo
     */
    public static void main(String[] args) {
        Thread[] hilos = new Thread[4];

        Mesa mesa = new MesaLocks(3);

        hilos[0] = new Agente("agente" , mesa);
        hilos[1] = new Fumador("fumador" , mesa, 1);
        hilos[2] = new Fumador("fumador" , mesa, 2);
        hilos[3] = new Fumador("fumador" , mesa, 3);

        for (Thread t : hilos) {
            t.start();
        }
    }
}
