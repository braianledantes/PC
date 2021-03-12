package productor_consumidor;

public class TestProductorConsumidor {

    public static void main(String[] args) {
        int cantConsumidores = 4;
        int cantProductores = 2;
        int capacidadBuffer = 4;

        Consumidor[] consumidores = new Consumidor[cantConsumidores];
        Productor[] productores = new Productor[cantProductores];

        Buffer<Integer> buffer = new Buffer<>(capacidadBuffer);

        for (int i = 0; i < consumidores.length; i++) {
            consumidores[i] = new Consumidor("Consumidor" + (i + 1), buffer);
        }

        for (int i = 0; i < productores.length; i++) {
            productores[i] = new Productor("Productor" + (i + 1), buffer);
        }

        for (Productor productore : productores) {
            productore.start();
        }

        for (Consumidor consumidore : consumidores) {
            consumidore.start();
        }
    }
}
