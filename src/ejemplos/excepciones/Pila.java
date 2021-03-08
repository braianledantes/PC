/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplos.excepciones;

/**
 *
 * @author Silvia
 */
public class Pila {
    TipoElemento dato;
    public Pila() {
        //...
    }
    
    // ...
    
    public void apilar (TipoElemento elem){
        // ...
        dato = elem;
    }
    
    public TipoElemento tope(){
        // ...
        return dato;
    }
    
    // ...
    
}    
