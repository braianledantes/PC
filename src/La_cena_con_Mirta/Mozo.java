package La_cena_con_Mirta;

public class Mozo extends Thread {
    private Mesa mesa;

    public Mozo(Mesa mesa) {
        this.mesa = mesa;
    }

    @Override
    public void run() {
        try {
            mesa.servir_comida();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
