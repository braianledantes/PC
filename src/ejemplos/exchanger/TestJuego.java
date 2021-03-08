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
public class TestJuego {
    
    public static void main(String args[]){    
        Exchanger<String>  exchanger = new Exchanger();
        Juego juego=new Juego();
        Jugador jugador1= new Jugador(exchanger,"angel",juego);
        Jugador jugador2= new Jugador(exchanger,"walter",juego);
         
        jugador1.start();
        jugador2.start();
    }
}
