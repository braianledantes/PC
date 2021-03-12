package carreraatletismo;

public class Corredor extends Thread {
    private final Carrera carrera;

    public Corredor(String name, Carrera carrera) {
        super(name);
        this.carrera = carrera;
    }

    @Override
    public void run() {
        carrera.esperarTestigo();
        carrera.correr();
        carrera.entregrarTestigo();
    }
}
