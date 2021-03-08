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
public interface TipoElemento {
    public boolean menor(TipoElemento elem);
    public boolean equals (TipoElemento elem);
    public String aCadena ();
}
