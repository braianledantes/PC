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
public class Empleado implements TipoElemento {
    String nombre;
    public Empleado(String cad){
        nombre = cad;
    }
    
    public boolean menor(TipoElemento elem){
      // ...
      return (nombre.compareTo(((Empleado) elem).nombre))<0;
    };
    
    public boolean equals (TipoElemento elem){
       // ...
       return true;
    };
    
    public String aCadena (){
      // ... 
      return nombre;
    };
    
}
