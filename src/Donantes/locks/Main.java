package Donantes.locks;

public class Main {

    /**
     * En un centro de hemoterapia se reciven donaciones de sangre. Para realizar las extracciones,
     * que toman un tiempo aleatorio, se cuenta con 4 camillas. Las personas que llegan a donar sangre
     * son atendidas en orden de llegada, pero mientras no haya camillas en la sala de espera, pueden
     * optar por utilizar una de las sillas o esperar parados.
     * En la salas de espera hay 9 revistas "muy interesanta" que las personas leen mientras esperan su
     * turno y un televisor sin audio sintonizado en el canal crónica-tv. Los donantes siempre prefieren
     * leer una revista, pero si no hay ninguna disponible miran el noticiero. Cuando se libera una camilla
     * se llama a la siguiente persona en la sala de espera, quien deja la revista si tenia una. Ni bien una
     * revista es liberada las personas mirando el televisor compiten por tomarla.
     *      a. Identificar los roles activos y pasivos compartidos en el escenario presentado.
     *      b. Dar una solucion uilizando mecanismos de sinrionizacion que modele el comportamiento explicado.
     */

    public static void main(String[] args) {
        Sala sala = new Sala(4, 9);
        Thread[] donantes = new Thread[10];

        for (int i = 0; i < donantes.length; i++) {
            donantes[i] = new Thread(new Donante(i, sala));
            donantes[i].start();
        }
    }
}
