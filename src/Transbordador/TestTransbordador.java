package Transbordador;

public class TestTransbordador {
    /*
     * Un transbordador permite pasar coches de un lado de un río al otro. Los
     * coches viajan por el lado este del río, cruzan el río y continúan su viaje por el lado oeste
     * (nunca vuelven). El transbordador tiene espacio para 10 coches y espera a estar lleno para
     * cruzar el río. Cuando ha cruzado y descargado los coches, vuelve vacío.
     * Considere en el transbordador operaciones ir y volver
     * - ir hace que el transbordador cruce con los coches
     * - volver lo hace volver vacío.
     * Se trata de implementar este problema con hilos, resolviendo la concurrencia con el empleo
     * de algún mecanismo de sincronización (monitor, semáforos, locks).
     *
     * Nota: Resolver el problema teniendo en cuenta que, en el caso en que se quiera
     * desbloquear a varios procesos debe hacerse en cadena, esto es, cada proceso debe
     * desbloquear al siguiente.
     */
    public static void main(String[] args) {
        Transbordador transbordador = new TransbordadorCountDownLatch();
        Manejador manejador = new Manejador("manejador" , transbordador);
        manejador.setPriority(Thread.MAX_PRIORITY);

        Thread[] hilos = new Thread[10];
        for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new Coche("Coche" + i, transbordador);
        }

        manejador.start();
        for (Thread t : hilos) {
            t.start();
        }
    }

}
