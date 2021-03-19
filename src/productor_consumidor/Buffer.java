package productor_consumidor;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer<P> {
    private final BlockingQueue<P> productos;
    private final Random random = new Random();
    private final Logger logger = Logger.getLogger(Buffer.class.getName());

    public Buffer(int capacidad) {
        //productos = new ArrayBlockingQueue<>(capacidad);
        productos = new LinkedBlockingDeque<>(capacidad);
    }

    public void agregarProducto(P producto) {
        try {
            Thread.sleep(random.nextInt(2000) + 1000);
            logger.log(Level.INFO, "--> " + Thread.currentThread().getName() + " produce " + producto);
            productos.put(producto);
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    public P obtenerProducto() {
        P producto = null;
        try {
            producto = productos.take();
            logger.log(Level.INFO,"<-- " + Thread.currentThread().getName() + " consume " + producto);
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return producto;
    }
}
