package Transbordador;

public class Manejador extends Thread {
    Transbordador transbordador;

    public Manejador(String name, Transbordador transbordador) {
        super(name);
        this.transbordador = transbordador;
    }

    @Override
    public void run() {
        while (true) {
            transbordador.ir();
            transbordador.volver();
        }
    }
}
