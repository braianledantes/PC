/*
 *
 * Clase Servicio
 *
 */
package ejemplos.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Sebastian
 */
public class Servicio implements Runnable {
    private String nombre;
    private int tiempoDeComienzo;
    private CountDownLatch cerrojo;

    public Servicio(String unNombre, int unTiempoDeComienzo, CountDownLatch unCerrojo) {
        this.nombre = unNombre;
        this.tiempoDeComienzo = unTiempoDeComienzo;
        this.cerrojo = unCerrojo;
    }

    public void run() {
        try {
            Thread.sleep(tiempoDeComienzo);
        } catch (Exception ex) {
            Logger.getLogger(Servicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(nombre + " en funcionamiento");
        cerrojo.countDown(); // Reduce el recuento del CountDownLatch en 1
    }
}