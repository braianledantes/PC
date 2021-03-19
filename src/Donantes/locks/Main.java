package Donantes.locks;

import java.util.concurrent.CountDownLatch;

public class Main {

    /**
     * En un centro de hemoterapia se reciben donaciones de sangre. Para realizar las extracciones,
     * que toman un tiempo aleatorio, se cuenta con 4 camillas. Las personas que llegan a donar sangre
     * son atendidas en orden de llegada, pero mientras no haya camillas en la sala de espera, pueden
     * optar por utilizar una de las sillas o esperar parados.
     * En la salas de espera hay 9 revistas "muy interesanta" que las personas leen mientras esperan su
     * turno y un televisor sin audio sintonizado en el canal crónica-tv. Los donantes siempre prefieren
     * leer una revista, pero si no hay ninguna disponible miran el noticiero. Cuando se libera una camilla
     * se llama a la siguiente persona en la sala de espera, quien deja la revista si tenia una. Ni bien una
     * revista es liberada las personas mirando el televisor compiten por tomarla.
     * <p>a. Identificar los roles activos y pasivos compartidos en el escenario presentado.</p>
     * <p>b. Dar una solucion uilizando mecanismos de sinrionizacion que modele el comportamiento explicado.</p>
     */

    public static void main(String[] args) {
        int cantCamillas = 4;
        int cantRevistas = 2;
        int cantDonantes = 15;

        Revista[] revistas = new Revista[cantRevistas];
        for (int i = 0; i < revistas.length; i++) {
            revistas[i] = new Revista("Revista" + (i + 1));
        }

        Sala sala = new SalaLocks(cantCamillas, cantRevistas, 15);
        CountDownLatch empezar = new CountDownLatch(1);
        Thread[] donantes = new Thread[cantDonantes];

        for (int i = 0; i < donantes.length; i++) {
            donantes[i] = new Donante("Donante-" + (i + 1), sala, empezar);
            donantes[i].start();
        }

        empezar.countDown();
    }
}
