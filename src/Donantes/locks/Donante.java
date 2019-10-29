package Donantes.locks;

public class Donante implements Runnable {
    private Sala sala;
    private int id;

    public Donante(int id, Sala sala) {
        this.sala = sala;
        this.id = id;
    }

    @Override
    public void run() {

        try {
            sala.entrar(id);
            sala.donar(id);
            sala.salir(id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
