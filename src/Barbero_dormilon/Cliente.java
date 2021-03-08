package Barbero_dormilon;

public class Cliente implements Runnable {
    private final Barberia barberia;
    private final int id;

    public Cliente(Barberia barberia, int i) {
        this.barberia = barberia;
        this.id=i;
    }

    @Override
    public void run() {
        while (true) {
            barberia.entrar(id);
            barberia.sentarseEnSillon(id);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
