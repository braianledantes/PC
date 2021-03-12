package productor_consumidor;

import java.util.Random;

public class Productor extends Thread {
    private final Buffer<Integer> buffer;

    public Productor(String name, Buffer<Integer> buffer) {
        super(name);
        this.buffer = buffer;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            Integer producto = random.nextInt(100);
            buffer.agregarProducto(producto);
        }
    }
}
