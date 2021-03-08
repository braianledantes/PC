package ejemplos.exchanger;

import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Walter Toloza
 */
public class Juego {  
    private boolean bandera=true;
    
    public String Jugar() {        
        String resultado[]= {"PIEDRA","PAPEL","TIJERA"};
        return resultado[(new Random()).nextInt(3)];
    }
    
    public synchronized void mostrarRonda(int i) {
        if (bandera) {
            System.out.println("\n RONDA " + i+"\n");
            bandera = false;
        } else {
            bandera = true;
        }
    }
    public String comparar(String propio, String oponente) {
        String sale;        
        if(propio.equals(oponente) ){
            sale="EMPATÉ";
        }
        else{
            sale=((propio.equals("PIEDRA") && oponente.equals("TIJERA")) || 
                  (propio.equals("PAPEL") && oponente.equals("PIEDRA")) || 
                  (propio.equals("TIJERA") && oponente.equals("PAPEL"))) ?
                  "GANÉ" : "PERDÍ";
        }
        return sale;
    }
}
