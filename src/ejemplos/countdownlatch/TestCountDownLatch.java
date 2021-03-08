/*
 * En este Test se mostrará de forma básica el uso de la herramienta de sincronización
 * CountDownLatch, explicando su utilidad y función.
 *
 * En este ejemplo se realiza un programa que simula los servicios que Java necesita
 * que esten listos para poder iniciarse y manejar cualquier petición
 * ( 3 Servicios: CacheService, AlertService, ValidationService).
 * Esto lo logramos mediante el uso del CountDownLatch.
 */
package ejemplos.countdownlatch;

/*
 * @author Sebastian Santalla
 */

import ejemplos.countdownlatch.Servicio;

import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch {

    public static void main(String[] args) {
        final CountDownLatch cerrojo = new CountDownLatch(3);
        Thread servicioCache = new Thread(new Servicio("Servicio de Cache", 1000, cerrojo));
        Thread servicioAlerta = new Thread(new Servicio("Servicio de Alerta", 1000, cerrojo));
        Thread servicioValidacion = new Thread(new Servicio("Servicio de Validación", 1000, cerrojo));

        servicioCache.start();
        servicioAlerta.start();
        servicioValidacion.start();

        /*
         * Java no debe comenzar a procesar ningun hilo hasta que todo el servicio este
         * activo y listo para hacer el trabajo. El cerrojo del CountDownLatch mantiene
         * inactivo (en espera) al hilo principal. Este cerrojo se inicia con 3 (Por los 3 servicios)
         * y por cada servicio que este activo y listo, el cerrojo se decrementa.
         * Al alcanzar el valor cero el hilo principal se libera, permitiendo el inicio de Java.
         */

        try {
            cerrojo.await(); // El hilo principal(Java) espera hasta que los sistemas se activen
            // (CountDownLatch llega a cero)
            System.out.println("Todos los Servicios estan listos y activos, Java se esta iniciando");
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

}


