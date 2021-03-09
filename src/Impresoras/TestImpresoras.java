package Impresoras;

public class TestImpresoras {
    /*
     * En un centro de impresión se cuenta con dos tipos distintos de impresoras para dar
     * servicio a los usuarios: impresoras de tipo A e impresoras de tipo B. Obviamente, el
     * número de impresoras de cada tipo es limitado: NumA impresoras de tipo A y NumB
     * impresoras de tipo B. Para imprimir un trabajo en una impresora de un tipo determinado (A
     * o B) es necesario hacer la solicitud indicando el tipo de impresión requerida. A la hora de
     * imprimir los trabajos se pueden considerar tres grupos distintos de procesos usuarios:
     *      1. Los que requieren una impresora de tipo A.
     *      2. Los que requieren una impresora de tipo B.
     *      3. Los que pueden utilizar una impresora de uno cualquiera de los dos tipos.
     * Los hilos usuarios generan trabajos y los imprimen. Como restricción: dos hilos no pueden
     * ejecutar simultáneamente las operaciones de impresión (Imprimir A o Imprimir B) sobre
     * una misma impresora.
     * Cuando un usuario quiera imprimir un trabajo deberá hacerlo sobre una impresora
     * compatible con él y que no esté siendo utilizada por otro usuario. En otro caso el proceso
     * deberá esperar.
     */
    public static void main(String[] args) {

    }
}
