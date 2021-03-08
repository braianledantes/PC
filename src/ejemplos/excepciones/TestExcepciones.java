/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplos.excepciones;

public class TestExcepciones {
    
    public static void pruebaCero(){
        int nro;
        
        nro = (int)(Math.random()*10);
        if (nro >5){
            System.out.println ( nro + " disparo excepcion UnaExc ");
            throw (new UnaExc());
        } else {
            System.out.println (nro + " disparo excepcion OtraExc ");
            throw (new OtraExc());
        }
    }

    public static void pruebaUno() throws Exception {
        try{
            pruebaCero();
        }   catch (OtraExc  se) {
              System.out.println (" pasa por manejador pruebaUno ");
            throw new Exception();
        } finally { System.out.println ("¿pasara por finally?????" );
          //  throw new UnaExc();
        }
        System.out.println ("¿pasara por aca?????" );
    }  
    
    public static void pruebaDos() {
    try{
        pruebaUno();
    }   catch (OtraExc  se) {
            System.out.println (" pasa por manejador pruebaDos ");
    }   catch (Exception ex){
            System.out.println (" pasa por el manejador de Exception en pruebaDOs");      
    }   finally {
         System.out.println ("estoy en el finally de prueba dos ");
    }
    
    System.out.println ("codigo final de pruebaDos ");
    }
    
    public static void main(String[] args) {
        
        Pila laPila = new Pila();
        
        //...
        Empleado unEmp = new Empleado("Juan");
        laPila.apilar(unEmp);
        System.out.println(((Empleado)laPila.tope()).aCadena());
        try {
            pruebaDos();
        } catch (UnaExc ne) {
            System.out.println (" pasa por manejador main ");
        } finally {
            System.out.println (" estoy en finally main");
        }
        
        System.out.println ("codigo del main ");
    }
    
}
