package Fabrica_de_vinos;

public class Fabricante implements Runnable {
    private Almacen almacen;

    public Fabricante(Almacen almacen) {
        this.almacen = almacen;
    }

    @Override
    public void run() {
        while (true) {
            try {
                almacen.iniciarMezcla();
                almacen.mezclar();
                almacen.dejarFermentar();
                almacen.tomar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
