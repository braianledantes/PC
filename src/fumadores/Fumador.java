package fumadores;

public class Fumador extends Thread {

    private final Mesa mesa;
    private final int ingrediente;

    public Fumador(String name, Mesa mesa, int ingrediente) {
        super(name);
        this.mesa = mesa;
        this.ingrediente = ingrediente;
    }

    @Override
    public void run() {
        while (true) {
            mesa.armarCigarrillo(ingrediente);
        }
    }
}
