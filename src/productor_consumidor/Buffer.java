package productor_consumidor;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer<P> {
    private final Semaphore producir;
    private final Semaphore consumir;
    private final BlockingQueue<P> productos;
    private final Random random = new Random();
    private final Logger logger = Logger.getLogger(Buffer.class.getName());

    public Buffer(int capacidad) {
        producir = new Semaphore(capacidad);
        consumir = new Semaphore(0);
        productos = new ArrayBlockingQueue<>(capacidad);
    }

    public void agregarProducto(P producto) {
        try {
            producir.acquire();
            Thread.sleep(random.nextInt(2000) + 1000);
            logger.log(Level.INFO, "--> " + Thread.currentThread().getName() + " produce " + producto);
            productos.add(producto);
            consumir.release();
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    public P obtenerProducto() {
        P producto = null;
        try {
            consumir.acquire();
            producto = productos.poll();
            logger.log(Level.INFO,"<-- " + Thread.currentThread().getName() + " consume " + producto);
            producir.release();
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return producto;
    }
}
