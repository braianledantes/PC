package Fabrica_de_vinos;

public class Reponedor implements Runnable {
    private Almacen almacen;

    public Reponedor(Almacen almacen) {
        this.almacen = almacen;
    }

    @Override
    public void run() {
        while (true) {
            almacen.reponerIngredientes();
        }
    }
}
