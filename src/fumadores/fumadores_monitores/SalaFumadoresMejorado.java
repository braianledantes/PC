package fumadores.fumadores_monitores;

public class SalaFumadoresMejorado extends SalaFumadores {
    protected Object cerrojoAgente = new Object();

    public SalaFumadoresMejorado() {
        ingredienteQueFalta = -1;
    }

    public synchronized void entraafumar(int id) throws InterruptedException {
        while (id != ingredienteQueFalta) {
            wait();
        }
    }

    public synchronized void terminarfumar() {
        ingredienteQueFalta = -1;
        synchronized (cerrojoAgente) {
            cerrojoAgente.notify();
        }
    }

    public void colocar(int i) {
        synchronized (cerrojoAgente) {
            while (ingredienteQueFalta != -1) {
                try {
                    cerrojoAgente.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            ingredienteQueFalta = i;
            hayIngrediente = true;
        }
        synchronized (this){
            notifyAll();
        }
    }
}
