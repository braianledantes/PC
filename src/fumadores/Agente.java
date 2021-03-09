package fumadores;

public class Agente extends Thread {

    private final Mesa mesa;

    public Agente(String name, Mesa mesa) {
        super(name);
        this.mesa = mesa;
    }

    @Override
    public void run() {
        while (true) {
            mesa.agregarIngredientes();
        }
    }
}
