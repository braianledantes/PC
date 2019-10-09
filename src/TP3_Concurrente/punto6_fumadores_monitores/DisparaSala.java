package TP3_Concurrente.punto6_fumadores_monitores;
/**
 * @author Braian Ledantes
 * @legajo FAI-1686
 */

/**
 * Considere un sistema formado por tres hilos fumadores que se pasan el día
 * armando cigarrillos y fumando. Para armar y fumar un cigarrillo necesitan tres
 * ingredientes: tabaco, papel y fósforos.
 * Cada fumador dispone de un surtido suficiente (para el resto de su vida) de
 * uno de los tres ingredientes. Cada fumador tiene un ingrediente diferente, es
 * decir, un fumador tiene una cantidad infinita de tabaco, el otro de papel y el
 * otro de fósforos. Hay también un hilo agente que pone dos de los tres
 * ingredientes encima de una mesa. El agente dispone de unas reservas infinitas
 * de cada uno de lostres ingredientes y escoge de forma aleatoria cuáles son los
 * ingredientes que pondrá encima de la mesa. Cuando los ha puesto, el fumador que
 * tiene el otro ingrediente puede armar su cigarrillo y fumar (los otros dos no).
 * Para ello toma los ingredientes, se arma un cigarrillo y se lo fuma.
 * Cuando termina de fumar vuelve a repetirse el ciclo. En resumen, el ciclo
 * que debe repetirse es :
 * “agente pone ingredientes → fumador hace cigarro → fumador fuma →
 * fumador termina de fumar → agente pone ingredientes → ...”
 * Es decir, en cada momento a lo sumo hay un fumador fumando un cigarrillo.
 */
public class DisparaSala {

    public static void main(String[] args) {
        SalaFumadores sala = new SalaFumadores();
        Fumador f1 = new Fumador(1,sala);
        Fumador f2 = new Fumador(2,sala);
        Fumador f3 = new Fumador(3,sala);
        Agente ag = new Agente(sala);

        Thread fumador1 = new Thread(f1, "Fumador" + 1);
        Thread fumador2 = new Thread(f2,"Fumador" + 2);
        Thread fumador3 = new Thread(f3, "Fumador" + 3);
        Thread agente = new Thread(ag, "Agente");

        fumador1.start();
        fumador2.start();
        fumador3.start();
        agente.start();
    }

}
