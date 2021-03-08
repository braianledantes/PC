/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplos.exchanger;

import java.util.concurrent.Exchanger;

/**
 *
 * @author Walter Toloza
 */
public class Jugador extends Thread {
    private Exchanger<String> exchanger;
    private Juego juego;
    private String nombre;

    public Jugador(Exchanger exchanger, String nombre, Juego j) {
        this.exchanger = exchanger;
        this.nombre = nombre;
        this.juego = j;
    }

    public void run() {
        String jugadaOponente, miJugada, resultado;
        for (int i = 1; i < 4; i = i + 1) {
            juego.mostrarRonda(i);
            try {
                miJugada = juego.Jugar();
                System.out.println(nombre + ": " + miJugada);
                jugadaOponente = exchanger.exchange(miJugada);
                resultado = juego.comparar(miJugada, jugadaOponente);
                System.out.println(nombre + ": " + resultado);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
