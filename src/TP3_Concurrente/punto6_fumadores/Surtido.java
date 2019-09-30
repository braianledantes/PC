package TP3_Concurrente.punto6_fumadores;

public abstract class Surtido {
    private int cantidad;

    public Surtido(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
