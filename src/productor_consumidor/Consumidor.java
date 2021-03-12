package productor_consumidor;

import java.util.Random;

public class Consumidor extends Thread {
    private final Buffer buffer;
    Random random = new Random();

    public Consumidor(String name, Buffer buffer) {
        super(name);
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            Object producto = buffer.obtenerProducto();
            try {
                Thread.sleep(random.nextInt(2000) + 8000);
            } catch (InterruptedException ignore) {
            }
        }
    }
}
